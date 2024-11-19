import requests
import json

def detect_language_from_api(code_snippet):
    url = 'https://guesslang.waterwater.moe/guess'
    
    headers = {
        'accept': '*/*',
        'accept-language': 'en-GB,en-US;q=0.9,en;q=0.8',
        'content-type': 'application/json',
        'origin': 'https://guesslang.waterwater.moe',
        'priority': 'u=1, i',
        'referer': 'https://guesslang.waterwater.moe/',
        'sec-ch-ua': '"Chromium";v="130", "Google Chrome";v="130", "Not?A_Brand";v="99"',
        'sec-ch-ua-mobile': '?0',
        'sec-ch-ua-platform': '"macOS"',
        'sec-fetch-dest': 'empty',
        'sec-fetch-mode': 'cors',
        'sec-fetch-site': 'same-origin',
        'user-agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36'
    }
    
    payload = {
        'text': code_snippet
    }
    
    try:
        response = requests.post(url, headers=headers, data=json.dumps(payload))
        response.raise_for_status()
        if response.status_code == 200:
            result = response.json()
            return result
        else:
            return f"Error: {response.status_code} - {response.text}"
    except requests.exceptions.RequestException as e:
        return f"Error: {str(e)}"

def is_java_code(snippet):
    result = detect_language_from_api(snippet)
    if result == "Error: 400 - Bad Request":
        return False
    if result['languageName'] == 'Java':
        return True
    return False

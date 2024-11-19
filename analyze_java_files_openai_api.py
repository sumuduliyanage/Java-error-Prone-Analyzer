import openai
import os
import csv

openai.api_key = "sk-...Paste your OpenAI API key here..."

def analyze_code_with_chatgpt(code_snippet):
    prompt = f"""
    Review this Java code snippet for potential error-prone issues and explain the flaws:
    {code_snippet}
    """
    try:
        response = openai.ChatCompletion.create(
            model="gpt-4o-mini",
            messages=[
                {"role": "system", "content": "You are an expert in Java programming and static code analysis."},
                {"role": "user", "content": prompt}
            ],
            max_tokens=500
        )
        return response['choices'][0]['message']['content'].strip()
    except Exception as e:
        return f"Error analyzing code: {str(e)}"

def read_java_files_from_folder(folder_path):
    code_snippets = []
    for file_name in os.listdir(folder_path):
        if file_name.endswith(".java"):
            file_path = os.path.join(folder_path, file_name)
            with open(file_path, mode="r", encoding="utf-8") as file:
                code_snippets.append((file_name, file.read()))
    return code_snippets

def save_analysis_to_csv(results, output_csv):
    with open(output_csv, mode="w", encoding="utf-8", newline="") as file:
        writer = csv.writer(file)
        writer.writerow(["File Name", "Analysis"])
        writer.writerows(results)

def main(folder_path, output_csv):
    java_files = read_java_files_from_folder(folder_path)
    print(f"Found {len(java_files)} Java files in {folder_path}.")

    analysis_results = []
    for file_name, code_snippet in java_files:
        print(f"Analyzing {file_name}...")
        analysis = analyze_code_with_chatgpt(code_snippet)
        analysis_results.append((file_name, analysis))

    save_analysis_to_csv(analysis_results, output_csv)
    print(f"Analysis results saved to {output_csv}.")

folder_path = "java_snippets/"
output_csv = "analysis_results_openai_api.csv"

if __name__ == "__main__":
    main(folder_path, output_csv)

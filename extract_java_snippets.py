import pandas as pd
from bs4 import BeautifulSoup
from java_language_detector_guesslang_api import is_java_code

# Function to extract code snippets from HTML body
def extract_code_snippets(body):
    soup = BeautifulSoup(body, 'html.parser')
    code_blocks = soup.find_all(['code', 'pre'])
    return [block.get_text() for block in code_blocks]

df = pd.read_csv("combined_data.csv")
answers = df[df['PostTypeId'] == 2]
answers = answers.sort_values(by="CreationDate", ascending=False)

code_snippets = []

for _, row in answers.iterrows():
    body = row['Body']
    snippets = extract_code_snippets(body)
    for snippet in snippets:
        if is_java_code(snippet):
            print(snippet)
            code_snippets.append(snippet)
        if len(code_snippets) >= 100:
            break
    if len(code_snippets) >= 100:
        break


# Save Java code snippets as separate .java files ####

import os

java_files_dir = "java_snippets"
os.makedirs(java_files_dir, exist_ok=True)
java_file_paths = []
for i, snippet in enumerate(code_snippets):
    file_path = os.path.join(java_files_dir, f"snippet_{i+1}.java")
    with open(file_path, 'w') as f:
        f.write(snippet)
    java_file_paths.append(file_path)

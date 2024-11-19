import pandas as pd

# Load all CSV files into DataFrames
df1 = pd.read_csv('csvs/QueryResults_1_2.csv')
df2 = pd.read_csv('csvs/QueryResults_3_4.csv')
df3 = pd.read_csv('csvs/QueryResults_5.csv')
df4 = pd.read_csv('csvs/QueryResults_6_7_8.csv')
df5 = pd.read_csv('csvs/QueryResults_9_10_11.csv')

# Combine them into a single DataFrame (stack them vertically)
combined_df = pd.concat([df1, df2, df3, df4, df5], ignore_index=True)
combined_df = combined_df.drop_duplicates()
combined_df = combined_df.sort_values(by='CreationDate', ascending=False)

# Save the combined DataFrame to a new CSV file
combined_df.to_csv('combined_data.csv', index=False)

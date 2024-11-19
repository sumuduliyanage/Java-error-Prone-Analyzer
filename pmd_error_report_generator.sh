#!/bin/bash
pmd check -d java_snippets/ -f text -R category/java/errorprone.xml -r pmd-error-prone-report.txt

echo "PMD check completed. Report saved to pmd-error-prone-report.txt."


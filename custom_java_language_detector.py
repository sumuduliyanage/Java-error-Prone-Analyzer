from pygments.lexers import guess_lexer
from pygments.util import ClassNotFound
import re

java_keywords = [
    r'\babstract\b', r'\bassert\b', r'\bboolean\b', r'\bbreak\b', r'\bbyte\b', r'\bcase\b', r'\bcatch\b',
    r'\bchar\b', r'\bclass\b', r'\bconst\b', r'\bcontinue\b', r'\bdefault\b', r'\bdo\b', r'\bdouble\b',
    r'\belse\b', r'\benum\b', r'\bextends\b', r'\bfinal\b', r'\bfinally\b', r'\bfloat\b', r'\bfor\b',
    r'\bgoto\b', r'\bif\b', r'\bimplements\b', r'\bimport\b', r'\binstanceof\b', r'\bint\b', r'\binterface\b',
    r'\blong\b', r'\bnative\b', r'\bnew\b', r'\bnull\b', r'\bprivate\b', r'\bprotected\b',
    r'\bpublic\b', r'\breturn\b', r'\bshort\b', r'\bstatic\b', r'\bstrictfp\b', r'\bsuper\b', r'\bswitch\b',
    r'\bsynchronized\b', r'\bthis\b', r'\bthrow\b', r'\bthrows\b', r'\btransient\b', r'\btry\b', r'\bvoid\b',
    r'\bvolatile\b', r'\bwhile\b',

    # Common Java methods
    r'System\.out\.println', r'System\.out\.print', r'System\.err', r'Thread\.sleep', r'Math\.(random|abs|pow)', 
    r'Integer\.parseInt', r'Double\.parseDouble', r'String\.valueOf', r'String\.equals', r'String\.contains', 
    r'String\.substring',

    # Java class names
    r'java\.util\.\*', r'java\.io\.\*', r'java\.net\.\*', r'java\.lang\.\*', r'java\.time\.\*', r'java\.sql\.\*',
    r'ArrayList', r'LinkedList', r'HashMap', r'HashSet', r'StringBuilder', r'StringBuffer', r'Scanner', r'Date', 
    r'SimpleDateFormat', r'File', r'FileReader', r'FileWriter',

    # Java log and framework patterns
    r'org\.apache', r'javax\.servlet', r'org\.springframework', r'@Component', r'@RestController', r'@Service',
    r'@Entity', r'@Autowired', r'@Configuration', r'@RequestMapping', r'@PostMapping', r'@GetMapping', 
    r'@PathVariable', r'@RequestParam',

    # Java exception handling keywords
    r'try', r'catch', r'finally', r'throw', r'throws', r'Exception', r'RuntimeException', r'NullPointerException',
    r'IOException', r'SQLException', r'FileNotFoundException', r'ClassNotFoundException', r'ParseException',

    # Java data types
    r'\bint\b', r'\blong\b', r'\bshort\b', r'\bbyte\b', r'\bchar\b', r'\bdouble\b', r'\bfloat\b', r'\bboolean\b',
    r'\bString\b', r'\bObject\b', r'\bList\b', r'\bSet\b', r'\bMap\b', r'\bArray\b', r'\bboolean\[\]', r'String\[\]',
    r'int\[\]', r'ArrayList<String>', r'\bObject\b',

    # Control flow keywords
    r'\bif\b', r'\belse\b', r'\bswitch\b', r'\bcase\b', r'\bbreak\b', r'\bcontinue\b', r'\bfor\b', r'\bwhile\b',
    r'\bdo\b', r'\btry\b', r'\bcatch\b', r'\bfinally\b',

    # Java Modifiers
    r'\bpublic\b', r'\bprivate\b', r'\bprotected\b', r'\bstatic\b', r'\bfinal\b', r'\babstract\b',
    r'\bsynchronized\b', r'\btransient\b', r'\bvolatile\b'
]

# List of unwanted lexer names
unwanted_lexers = ["Text only", "Genshi", "XML", "YAML", "INI", "Markdown", "HTML", "CSS", "JSON", "World of Warcraft TOC", "ActionScript 3","GAS", "scdoc", "Perl6", "Objective-C", "XSLT"]

def is_keywords_matching(snippet):
    for pattern in java_keywords:
        if re.search(pattern, snippet):
            return True
    return False

def is_single_line_code(code_snippet):
    return '\n' not in code_snippet

def is_java_code(snippet):
    if is_single_line_code(snippet):
        return False
    java_keywords_pattern = re.compile(r'(' + '|'.join(java_keywords) + r')')
    if java_keywords_pattern.search(snippet):
        if is_keywords_matching(snippet):
            try:
                lexer = guess_lexer(snippet)
                lexer_name = lexer.name
                if lexer_name not in unwanted_lexers:
                    return True
                else:
                    return False
            except ClassNotFound:
                return False
        else:
            return False
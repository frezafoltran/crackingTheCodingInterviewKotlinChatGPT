import re
import json
import PyPDF2

def titleMapper(title):
    if title == None:
        return "RemoveDups"
    elif title == "OPaintFill":
        return "PaintFill"
    else:
        return title

def extract_paragraphs(text):
    pattern = r"\d+\.\d+\s+\S.*?:"
    pattern = r"Hints:\s*(#\d+(,\s*#\d+)*)?\s*"
    matches = re.finditer(pattern, text, flags=re.MULTILINE)
    return matches


def clean_question(text):
    pattern = r"Hints:#\s*\d+(?:\s*,\s*#\d+)*\s*[\n\.]+|[\n\.\-_·]+"
    return re.sub(pattern, "", text).replace("\u2022", "").replace("\ufffd", "").strip()

def write_algo_questions(lst):

    with open("./crackingCodingInterview/algo_questions.json", 'w') as file:
        json.dump(lst, file, indent=4)


def find_label_end_index(text, label = "Interview Questions"):
    res = text.find(label)
    return  res + len(label) if res != -1 else res

def extract_question_title(s):
    pattern = r'\d+\.\s*(.*?):'
    match = re.search(pattern, s)
    if not match:
        match = re.search(pattern, s)
        
    if match:
        return clean_title(match.group(1).strip())
    else:
        tmp = s.find(":")
        if tmp != -1:
            return clean_title(s[:tmp])
        return None


def clean_title(s):
    parts = s.split(" ")
    out = ''
    for part in reversed(parts):
        if any(c.isdigit() for c in part):
            return out.strip()
        out = part.strip() + out.strip()

    return out.strip()


with open('cracking.pdf', 'rb') as file:
    pdf_reader = PyPDF2.PdfReader(file)
    num_pages = len(pdf_reader.pages)
    
    algo_questions = pdf_reader.pages[99:148]
    #algo_questions = pdf_reader.pages[100:101]

    prev_match_end = -1
    out = []

    for page_obj in algo_questions:

        # Extract the text from the page
        text = page_obj.extract_text()
        print(text)

        tmp = find_label_end_index(text)
        if(tmp != -1):
            prev_match_end = tmp
            out.append([])
        else:
            prev_match_end = 0
    
        matches = extract_paragraphs(text)

        # Print the matching paragraphs
        for i, match in enumerate(matches):
       
            dirty_text = text[prev_match_end:match.start()]
            clean_text = clean_question(dirty_text)
            title = extract_question_title(clean_text)
            out[-1].append([titleMapper(title), clean_text])
            
            prev_match_end = match.end()
    
    write_algo_questions(out)

 
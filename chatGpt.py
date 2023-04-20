import openai
import json
import autopep8
import os

kotlin_keywords = ["private", "public", "null", "import", "@", "fun", "class", "val", "var", "interface", "object", "enum", "return", "if", "else", "for", "while", "do", "when", "try", "catch", "finally", "throw", "throws", "break", "continue"]
kotlin_mid_keywords = ["\"", "$", "++", "--", "+=", "=", "[", "]", "}", "{", "(", ")", "->", ">", "<"]

openai.api_key = OPENAI_API_KEY

def query(question, title):
    prompt = f"Using Kotlin, generate a class with the answer and a class with unit tests to validate the answer. Also include the time and space complexity of the algorithm with an explanation. For example:"
    return '''
Given: 
Title: SortStack. Question: Write a program to sort a stack such that the smallest items are on the top. You can use an additional temporary stack, but you may not copy the elements into any other data structure (such as an array). The stack supports the following operations: push, pop, peek, and is Empty
Return:

class SortStack: Stack<Double>() {
   // time complexity: O(n), where n is the length of stack
   // space complexity: O(n^2) 

   fun sort() {
       val helper = Stack<Double>()
       while(isNotEmpty()) {
           val popped = pop()
           while(helper.isNotEmpty() && helper.peek() > popped){
               push(helper.pop())
           }
           helper.push(popped)
       }
       while(helper.isNotEmpty()){
           push(helper.pop())
       }
   }
}

class SortStackTest {


   @Test
   fun `empty`() {
       val sorted = Stack<Double>().apply{
           listOf(1.0, 2.0, 4.0, 5.0, 6.0).sortedDescending().forEach { push(it) }
       }
       SortStack().apply {
           push(6.0)
           push(2.0)
           push(1.0)
           push(5.0)
           push(4.0)
           sort()
           assertEquals(sorted, this)
       }
   }
}
Given\n''' + f"Title: {title}. Question: {question}" + "\n" + "Return class with algorithm to answer question and a test class"


def ask_chatgpt(question, title, chapter, get_from_local = True):
    
    if get_from_local:
        with open(f"./gptAnswers/chapter{chapter}/{title}.kt", "r") as f:
            file_contents = f.read()
        return file_contents
    
    prompt = query(question, title)
    completion = openai.ChatCompletion.create(
        model="gpt-3.5-turbo", 
        messages = [
            {"role": "user", "content" : prompt},
        ]
    )
    print(completion.choices[0])
    return completion.choices[0].message.content


def is_kotlin_code(line):
    if len(line.strip()) == 0:
        return False
    if len(line.strip().split(" ")) == 1 and not line.strip().endswith(":"):
        return True
    if line.strip().find("//") > 0:
        return True
    if line.isupper():
        return True
    if len(line.strip().split(" ")) > 30:
        return False
    return line.strip().startswith(tuple(f"{kw}" for kw in kotlin_keywords)) or any(item in line.strip() for item in kotlin_mid_keywords)


def save_code(answer, title, chapter):

    output_dir = f'./solutions/chapter{chapter}'
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    formatted_code = []
    for line in autopep8.fix_code(answer, options={'max_line_length': 120}).split("\n"):
        prepend = "//" if (len(line) > 0 and not line.strip().startswith("//")) else ""
        formatted_code.append(line if is_kotlin_code(line) else f"{prepend}{line}")

    with open(output_dir + f"/{title}.kt", 'w') as output_file:
        output_file.write("\n".join(formatted_code))


def save_gpt(answer, title, chapter):

    output_dir = f'./gptAnswers/chapter{chapter}'
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    with open(output_dir + f"/{title}.kt", 'w') as output_file:
        output_file.write(answer)


def solve_cracking_coding_interview():

    with open('./crackingCodingInterview/algo_questions.json', 'r') as f:
        data = json.load(f)

    requestTitles = {}
    chapters = {7}

    for i, chapter in enumerate(data):

        if i not in chapters:
            continue
        
        for title, question in chapter:
            
            if title in requestTitles or len(requestTitles) == 0:
                answer = ask_chatgpt(question, title, i + 1, get_from_local=False)

                save_gpt(answer, title, i + 1)
                save_code(answer, title, i + 1)

       

solve_cracking_coding_interview()   
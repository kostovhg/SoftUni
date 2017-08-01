using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace p04_JOSNParse
{
    class JOSNParse
    {
        class Student
        {
            public string Name { get; set; }
            public int Age { get; set; }
            public List<int> Grades { get; set; }

            public Student(string input)
            {

                string pattern =
                   @"(?:.+:\"")(?<name>.+)(?:\"",.+:)(?<age>[\d]+)(?:,.+:)\[(?<grades>.*)\]";
                Regex regex = new Regex(pattern);
                var match = regex.Match(input);
                this.Name = match.Groups["name"].Value;
                this.Age = int.Parse(match.Groups["age"].Value);
                this.Grades = new List<int>( match.Groups["grades"].Value
                    .Split(new string[] { ", "}, 
                    StringSplitOptions.RemoveEmptyEntries)
                    .Select(int.Parse)
                    .ToList());
            }

        }
        class JSONStringify
        {
            static void Main(string[] args)
            {
                string input = Console.ReadLine();
                input = input.Substring(2, input.Length - 2 - 2);
                List<Student> students = new List<Student>();
                foreach (string student in input.Split(
                    new string[] {"},{"},
                    StringSplitOptions.RemoveEmptyEntries).ToList())
                {
                    students.Add(new Student(student));
                }
                foreach (Student student in students)
                {
                    Console.WriteLine("{0} : {1} -> {2}",
                        student.Name, student.Age, 
                        student.Grades.Count != 0 ?
                        string.Join(", ", student.Grades)
                        : "None");
                }
            }
        }
    }
}

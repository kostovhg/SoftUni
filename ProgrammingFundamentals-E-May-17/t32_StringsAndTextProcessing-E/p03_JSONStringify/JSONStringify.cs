using System;
using System.Collections.Generic;
using System.Linq;

namespace p03_JSONStringify
{
    class Student
    {
        public string Name { get; set; }
        public int Age { get; set; }
        public List<int> Grades { get; set; }

    }
    class JSONStringify
    {
        static void Main(string[] args)
        {
            string input;
            List<Student> students = new List<Student>();
            while (!"stringify"
                .Equals(input = Console.ReadLine()))
            {
                string[] tokens = input
                    .Split(new char[] { ' ', ':', '-', '>', ',' },
                    StringSplitOptions.RemoveEmptyEntries);
                string name = tokens[0];
                int age = int.Parse(tokens[1]);
                List<int> grades = tokens.Skip(2)
                    .Select(int.Parse).ToList();
                students.Add(new Student() {
                Name = name, Age = age, Grades = grades});

            }
            Console.WriteLine("[{0}]", string.Join(",",
                students
                .Select(x => 
                "{name:" + "\"" + x.Name
                    + "\"" + ",age:" + x.Age
                    + ",grades:[" + string.Join(", ", x.Grades) + "]}")));
        }
    }
}

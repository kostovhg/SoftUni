using System;
using System.Collections.Generic;
using System.Linq;

namespace p01_Exercises
{
    class Exercise
    {
        public string Topic { get; set; }
        public string CourseName { get; set; }
        public string JudgeContestLink { get; set; }
        public List<string> Problems { get; set; }

        public Exercise(string topic, string courseName, string judgeLink, List<string> problems)
        {
            this.Topic = topic;
            this.CourseName = courseName;
            this.JudgeContestLink = judgeLink;
            this.Problems = problems;
        }

    }
    class Exercises
    {
        static void Main(string[] args)
        {
            List<Exercise> exercises = new List<Exercise>();

            string input;
            while (!"go go go".Equals(input = Console.ReadLine()))
            {
                string[] tokens = input.Split(new string[] { " -> " },
                    StringSplitOptions.RemoveEmptyEntries);
                string topic = tokens[0];
                string courseName = tokens[1];
                string judgeLink = tokens[2];
                List<string> problems = tokens[3].Split(new string[] { ", " },
                    StringSplitOptions.RemoveEmptyEntries).ToList();
                exercises.Add(new Exercise(topic, courseName, judgeLink, problems));
            }

            foreach (Exercise ex in exercises)
            {
                Console.WriteLine("Exercises: {0}", ex.Topic);
                Console.WriteLine("Problems for exercises and homework for the \"{0}\" course @ SoftUni.", ex.CourseName);
                Console.WriteLine("Check your solutions here: {0}", ex.JudgeContestLink);
                for (int i = 1; i <= ex.Problems.Count; i++)
                {
                    Console.WriteLine("{0}. {1}", i, ex.Problems[i - 1]);
                }
            }
        }
    }
}

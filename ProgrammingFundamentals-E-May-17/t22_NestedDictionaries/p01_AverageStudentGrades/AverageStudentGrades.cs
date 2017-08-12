using System;
using System.Collections.Generic;
using System.Linq;

namespace p01_AverageStudentGrades
{
    class AverageStudentGrades
    {
        static void Main(string[] args)
        {
            var studentsData = new Dictionary<string, List<double>>();
            int inputCnt = int.Parse(Console.ReadLine());
            for (int cnt = 0; cnt < inputCnt; cnt++)
            {
                string[] inputTokens = Console.ReadLine().Split(' ');

                string name = inputTokens[0];
                double grade = double.Parse(inputTokens[1]);
                if (!studentsData.ContainsKey(name))
                {
                    studentsData.Add(name, new List<double>());
                }
                studentsData[name].Add(grade);
            }
            foreach (var item in studentsData)
            {
                Console.Write("{0} -> ", item.Key);
                foreach (var deg in item.Value)
                {
                    Console.Write("{0:F2} ", deg);
                }
                Console.WriteLine("(avg: {0:F2})", item.Value.Average());
            }
        }
    }
}

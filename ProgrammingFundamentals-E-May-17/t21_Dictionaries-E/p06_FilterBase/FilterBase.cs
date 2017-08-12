using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p06_FilterBase
{
    class FilterBase
    {
        static void Main(string[] args)
        {
            Dictionary<string, string> employeeAge = new Dictionary<string, string>();
            Dictionary<string, string> employeeSalary = new Dictionary<string, string>();
            Dictionary<string, string> employeePosition = new Dictionary<string, string>();

            string input = Console.ReadLine();
            while (!input.Equals("filter base"))
            {
                string[] tokens = input.Split();
                string name = tokens[0];
                string data = tokens[2];
                int age = 0;
                double salary = 0;
                if (int.TryParse(data, out age))
                {
                    employeeAge.Add(name, age.ToString());
                }
                else if (double.TryParse(data, out salary))
                {
                    employeeSalary.Add(name, string.Format( "{0:F2}", salary));
                }
                else
                {
                    employeePosition.Add(name, data);
                }

                input = Console.ReadLine();
            }

            input = Console.ReadLine();
            switch (input)
            {
                case "Age": printDictionary(employeeAge, input); break;
                case "Salary": printDictionary(employeeSalary, input); break;
                case "Position": printDictionary(employeePosition, input); break;
                default:
                    break;
            }
        }

        private static void printDictionary(Dictionary<string, string> dic, string param)
        {
            foreach (var item in dic)
            {
                Console.WriteLine($"Name: {item.Key}");
                Console.WriteLine($"{param}: {item.Value}");
                Console.WriteLine(new string('=', 20));
            }
        }
    }
}

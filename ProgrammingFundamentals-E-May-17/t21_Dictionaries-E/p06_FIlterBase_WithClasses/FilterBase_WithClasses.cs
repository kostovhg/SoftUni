using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p06_FilterBase_WithClasses
{
    public class Employ
    {
        public string Name { get; set; }
        public int Age { get; set; }
        public double Salary { get; set; }
        public string Position { get; set; }

        public Employ() { }
        public void AddData(string data)
        {
            int age;
            double salary;
            if (int.TryParse(data, out age))
            {
                Age = age;
            }
            else if (double.TryParse(data, out salary))
            {
                Salary = salary;
            }
            else
            {
                Position = data;
            }
        }

    }
    class FilterBase_WithClasses
    {
        static void Main(string[] args)
        {
            Dictionary<string, Employ> persons = new Dictionary<string, Employ>();
            string input = Console.ReadLine();
            while (!input.Equals("filter base"))
            {
                string[] tokens = input.Split();
                string name = tokens[0];
                string data = tokens[2];
                int age = 0;
                double salary = 0;
                
                if (!persons.ContainsKey(name))
                {
                    persons.Add(name, new Employ());
                }
                persons[name].Name = name;
                persons[name].AddData(data);

                input = Console.ReadLine();
            }

            input = Console.ReadLine();
            switch(input)
            {
                case "Age":
                    foreach (var person in persons.Values.Where(x => x.Age != 0))
                    {
                        Console.WriteLine($"Name: {person.Name}");
                        Console.WriteLine($"{input}: {person.Age}");
                        Console.WriteLine(new string('=', 20));
                    }
                    break;
                case "Salary":
                    foreach (var person in persons.Values.Where(x => x.Salary != 0.0))
                    {
                        Console.WriteLine($"Name: {person.Name}");
                        Console.WriteLine($"{input}: {person.Salary:F2}");
                        Console.WriteLine(new string('=', 20));
                    }
                    break;
                case "Position":
                    foreach (var person in persons.Values.Where(x => x.Position != null ))
                    {
                        Console.WriteLine($"Name: {person.Name}");
                        Console.WriteLine($"{input}: {person.Position}");
                        Console.WriteLine(new string('=', 20));
                    }
                    break;
            }
        }
    }
}

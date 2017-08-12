using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p03_MixedPhones
{
    class MixedPhones
    {
        static void Main(string[] args)
        {
            string input = Console.ReadLine();
            SortedDictionary<string, ulong> output = new SortedDictionary<string, ulong>();
            while (!input.Equals("Over"))
            {
                string[] tokens = input.Split(new char[] { ' ',':'}, StringSplitOptions.RemoveEmptyEntries);
                string name = tokens[0];
                ulong num;
                bool correct = ulong.TryParse(tokens[1], out num);
                if (correct)
                {
                    output.Add(name, num);
                }
                else
                {
                    output.Add(tokens[1], ulong.Parse(name));
                }

                input = Console.ReadLine();
            }

            foreach (string name in output.Keys)
            {
                Console.WriteLine("{0} -> {1}", name, output[name]);
            }
        }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p02_Dict_Ref
{
    class Dict_Ref
    {
        static void Main(string[] args)
        {
            // Prepare the dictionary to keep unique names as keys
            // and values for them
            Dictionary<string, int> dicRef = new Dictionary<string, int>();

            // read initial input
            string input = Console.ReadLine();

            // while input is different from "end"
            while (!input.Equals("end"))
            {
                string[] data = input.Split(new char[] { ' ', '=' }, StringSplitOptions.RemoveEmptyEntries);
                int num;
                if (int.TryParse(data[1], out num))
                {
                    dicRef[data[0]] = num;
                }
                else if (dicRef.ContainsKey(data[1]))
                {
                    dicRef[data[0]] = dicRef[data[1]];
                }

                input = Console.ReadLine();
            }

            foreach (KeyValuePair<string, int> item in dicRef)
            {
                Console.WriteLine("{0} === {1}",
                    item.Key, item.Value);
            }
        }
    }
}

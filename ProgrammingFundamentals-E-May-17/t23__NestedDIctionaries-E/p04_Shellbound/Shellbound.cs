using System;
using System.Collections.Generic;

namespace p04_Shellbound
{
    class Shellbound
    {
        static void Main(string[] args)
        {
            Dictionary<string, List<string>> shells = new Dictionary<string, List<string>>();
            string input = Console.ReadLine();

            while(!"Aggregate".Equals(input))
            {
                string[] tokens = input.Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries);
                if (!shells.ContainsKey(tokens[0]))
                    shells.Add(tokens[0], new List<string>());
                if(!shells[tokens[0]].Contains(tokens[1]))
                    shells[tokens[0]].Add(tokens[1]);

                input = Console.ReadLine();
            }

            foreach (string region in shells.Keys)
            {
                uint sum = 0;
                byte count = 0;
                foreach (string shell in shells[region])
                {
                    sum += uint.Parse(shell);
                    count++;
                }
                Console.WriteLine("{0} -> {1} ({2})", region, string.Join(", ", shells[region]), sum-(sum/count));
            }
        }
    }
}

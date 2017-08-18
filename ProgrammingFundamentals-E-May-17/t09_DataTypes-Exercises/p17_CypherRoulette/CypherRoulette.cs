using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p17_CypherRoulette
{
    class CypherRoulette
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            StringBuilder output = new StringBuilder();
            bool append = true;
            string preInput = String.Empty;

            for (int i = 0; i < n; i++)
            {
                string input = Console.ReadLine();
                if (input.Equals(preInput))
                {
                    output.Clear();
                    if (input.Equals("spin"))
                        i--;
                }
                else
                {
                    if (!input.Equals("spin"))
                    {
                        if (append)
                            output.Append(input);
                        else
                            output.Insert(0, input);
                    }
                    else
                    {
                        i--;
                        append = !append;
                    }
                }
                
                preInput = input;
            }
            Console.WriteLine(output);
        }
    }
}

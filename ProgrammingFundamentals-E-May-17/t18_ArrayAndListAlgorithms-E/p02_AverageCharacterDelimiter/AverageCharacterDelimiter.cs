using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p02_AverageCharacterDelimiter
{
    class AverageCharacterDelimiter
    {
        static void Main(string[] args)
        {
            string[] initial = Console.ReadLine().Split();
            int sum = 0;
            int letters = 0;
            foreach (string member in initial)
            {
                foreach (char l in member)
                {
                    sum += l;
                    letters++;
                }
            }
            char delim = (char)Math.Floor((double)sum / letters);
            Console.WriteLine(string.Join(delim.ToString().ToUpper(), initial));
        }
    }
}

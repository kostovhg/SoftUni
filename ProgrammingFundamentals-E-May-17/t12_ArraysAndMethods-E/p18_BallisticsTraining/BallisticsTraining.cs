using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p18_BallisticsTraining
{
    class BallisticsTraining
    {
        static void Main(string[] args)
        {
            double[] possition = Array.ConvertAll(Console.ReadLine().Split(), double.Parse);
            string[] arr = Console.ReadLine().Split();
            double x = 0.0;
            double y = 0.0;

            for (int i = 0; i < arr.Length - 1 ; i += 2)
            {
                var val = double.Parse(arr[i + 1]);
                switch (arr[i])
                {
                    case "left": x -= val; break;
                    case "right": x += val; break;
                    case "up": y += val; break;
                    case "down": y -= val; break;
                    default:
                        break;
                }
            }
            Console.WriteLine($"firing at [{x}, {y}]");
            if (possition[0].Equals(x) && possition[1].Equals(y))
            {
                Console.WriteLine(@"got 'em!");
            }
            else
            {
                Console.WriteLine("better luck next time...");
            }
        }
    }
}

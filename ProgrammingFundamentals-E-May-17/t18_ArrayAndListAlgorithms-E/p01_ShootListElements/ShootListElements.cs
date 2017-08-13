using System;
using System.Collections.Generic;
using System.Linq;

namespace p01_ShootListElements
{
    class ShootListElements
    {
        static void Main(string[] args)
        {
            List<int> iList = new List<int>(3);
            string input = Console.ReadLine();
            while (!input.Equals("bang"))
            {
                iList.Insert(0, int.Parse(input));
                input = Console.ReadLine();
            }
            int shooted = 0;
            while (!input.Equals("stop"))
            {
                if (iList.Count == 0)
                {
                    Console.WriteLine("nobody left to shoot! last one was {0}", shooted);
                    return;
                }
                for (int i = 0; i < iList.Count; i++)
                {
                    if (iList[i] <= iList.Average())
                    {
                        shooted = iList[i];
                        iList.RemoveAt(i);
                        break;
                    }
                }
                Console.WriteLine("shot {0}", shooted);
                iList = iList.Select(x => x - 1).ToList();
                input = Console.ReadLine();
            }
            if (iList.Count != 0)
            { 
                Console.WriteLine("survivors: {0}", string.Join(" ", iList));
            }
            else
            {
                Console.WriteLine("you shot them all. last one was {0}", shooted);
            }

        }
    }
}

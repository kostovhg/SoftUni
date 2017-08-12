using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p06_Extremums
{
    class Extremums
    {
        static void Main(string[] args)
        {
            uint[] inArray = Console.ReadLine().Split().Select(uint.Parse).ToArray();
            string minOrMax = Console.ReadLine();
            uint[] outArray = new uint[inArray.Length];
            if (minOrMax.Equals("Min"))
            {
                outArray = rotateForMinimum(inArray);
            }
            else
            {
                outArray = rotateForMaximum(inArray);
            }
        }

        private static uint[] rotateForMaximum(uint[] inArray)
        {
            
        }

        private static uint[] rotateForMinimum(uint[] inArray)
        {
            throw new NotImplementedException();
        }
    }
}

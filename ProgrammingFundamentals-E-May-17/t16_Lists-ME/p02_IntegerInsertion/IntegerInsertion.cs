using System;
using System.Collections.Generic;
using System.Linq;

namespace p02_IntegerInsertion
{
    class IntegerInsertion
    {
        static void Main(string[] args)
        {
            List<int> iList = Console.ReadLine().Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries).Select(int.Parse).ToList();
            string sNum = Console.ReadLine();
            while (!sNum.Equals("end"))
            {
                iList.Insert(sNum[0] - '0', int.Parse(sNum));
                sNum = Console.ReadLine();
            }
            Console.WriteLine(string.Join(" ", iList));
        }
    }
}

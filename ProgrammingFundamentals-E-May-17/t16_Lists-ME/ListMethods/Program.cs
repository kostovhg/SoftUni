using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ListMethods
{
    class ListMethods
    {
        static void Main(string[] args)
        {
        }

        private static List<int> readIntegerList()
        {
            List<int> list = Console.ReadLine().Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries).Select(int.Parse).ToList();
            return list;
        }
    }
}

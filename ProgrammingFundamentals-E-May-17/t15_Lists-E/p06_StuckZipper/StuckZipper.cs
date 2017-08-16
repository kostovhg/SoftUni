using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p06_StuckZipper
{
    class StuckZipper
    {
        static void Main(string[] args)
        {
            List<int> firstList = Console.ReadLine().Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries).Select(int.Parse).ToList();
            List<int> secondList = Console.ReadLine().Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries).Select(int.Parse).ToList();
            clearLists(new List<List<int>> { firstList, secondList });
            //List<string> output = secondList.Zip(firstList, (x, y) => x + " " + y).ToList();
            List<int> output = new List<int>();
            int len = Math.Max(firstList.Count, secondList.Count);
            for (int i = 0; i < len; i++)
            {
                if (i < secondList.Count) output.Add(secondList[i]);
                if (i < firstList.Count) output.Add(firstList[i]);
            }
            Console.WriteLine(string.Join(" ", output));
        }

        private static void clearLists(List<List<int>> lists)
        {

            byte minDigits = byte.MaxValue;

            foreach (List<int> list in lists)
            {
                foreach (int num in list)
                {
                    int number = Math.Abs(num).ToString().Length;
                    if (number < minDigits) minDigits = (byte)number;
                }
            }

            foreach (List<int> list in lists)
            {
                for (int i = 0; i < list.Count; i++)
                {
                    if (Math.Abs(list[i]).ToString().Length > minDigits)
                    {
                        list.RemoveAt(i);
                        i--;
                    }
                }
            }

            return;
        }
    }
}

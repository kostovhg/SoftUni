using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p06_Winecraft
{
    class Winecraft
    {
        private static List<int> readIntegerList()
        {
            List<int> list = Console.ReadLine().Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries).Select(int.Parse).ToList();
            return list;
        }
        static void Main(string[] args)
        {
            List<int> grapes = readIntegerList();
            int growthDays = int.Parse(Console.ReadLine());
            while (grapes.Count(x => x > growthDays) < growthDays)
            {
                for (int i = 1; i < grapes.Count - 1; i++)
                {
                    if (grapes[i] > grapes[i - 1] && grapes[i] < grapes[i + 1])
                    {


                    }
                }
            }
        }
    }
}

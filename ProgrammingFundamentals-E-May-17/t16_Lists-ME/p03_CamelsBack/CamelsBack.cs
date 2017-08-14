using System;
using System.Collections.Generic;
using System.Linq;

namespace p03_CamelsBack
{
    class CamelsBack
    {
        static void Main(string[] args)
        {
            List<string> town = Console.ReadLine().Split(new char[] { ' ' }, StringSplitOptions.RemoveEmptyEntries).ToList();
            int camelBack = int.Parse(Console.ReadLine());
            int rounds = 0;
            while (town.Count > camelBack)
            {
                town.RemoveAt(0);
                town.RemoveAt(town.Count - 1);
                rounds++;
            }
            Console.Write((rounds > 0) ? $"{rounds} rounds\r\nremaining: " : "already stable: ");
            Console.WriteLine(string.Join(" ", town));
        }
    }
}

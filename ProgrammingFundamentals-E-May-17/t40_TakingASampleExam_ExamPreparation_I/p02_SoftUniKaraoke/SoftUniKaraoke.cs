using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace p02_SoftUniKaraoke
{
    class SoftUniKaraoke
    {
        static void Main(string[] args)
        {
            string[] singers = Console.ReadLine().Split(new string[] { ", " }, StringSplitOptions.RemoveEmptyEntries);
            string[] songs = Console.ReadLine().Split(new string[] { ", " }, StringSplitOptions.RemoveEmptyEntries);
            Dictionary<string, SortedSet<string>> results = new Dictionary<string, SortedSet<string>>();
            string input;
            while (!"dawn".Equals(input = Console.ReadLine()))
            {
                string[] tokens = input.Split(new string[] { ", " }, StringSplitOptions.RemoveEmptyEntries);
                string name = tokens[0];
                string song = tokens[1];
                string award = tokens[2];
                if (!singers.Contains(name) || !songs.Contains(song)) continue;
                if (!results.ContainsKey(name))
                {
                    results.Add(name, new SortedSet<string>() { award });
                }
                else
                {
                    results[name].Add(award);
                }
            }
            if (results.Values.Count < 1)
            {
                Console.WriteLine("No awards");
                return;
            }
            foreach (var singer in results
                .OrderByDescending(e => e.Value.Count)
                .ThenBy(x => x.Key))
            {
                string participant = singer.Key;
                Console.WriteLine("{0}: {1} awards", singer.Key, singer.Value.Count);
                foreach (var award in singer.Value.OrderBy(x => x))
                {
                    Console.WriteLine("--{0}", award);
                }
            }

        }
    }
}

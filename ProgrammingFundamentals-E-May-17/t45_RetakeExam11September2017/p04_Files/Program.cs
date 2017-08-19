using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace p04_Files
{
    class Program
    {
        static void Main(string[] args)
        {
            int inputs = int.Parse(Console.ReadLine());
            Dictionary<string, Dictionary<string, ulong>> files = new Dictionary<string, Dictionary<string, ulong>>();
            string input;
            for (int i = 0; i < inputs; i++)
            {
                input = Console.ReadLine();
                int first = input.IndexOf('\\');
                int last = input.LastIndexOf('\\');
                string root = input.Substring(0, first);
                string path = input.Substring(first, input.LastIndexOf(';') - first);
                ulong size = ulong.Parse(input.Substring(input.LastIndexOf(';') + 1));
                if (!files.ContainsKey(root)) files.Add(root, new Dictionary<string, ulong>());
                if (!files[root].ContainsKey(path)) files[root].Add(path, 0L);
                files[root][path] = size;
            }
            Match m = Regex.Match(Console.ReadLine(), @"(?'ext'\w+) in (?'path'[^\n]+)");
            string reqExt = m.Groups["ext"].Value;
            string reqRoot = m.Groups["path"].Value;
            if (files.ContainsKey(reqRoot))
            {
                var filtered = files[reqRoot]
                    .Where(f => f.Key.Substring(f.Key.LastIndexOf('.') + 1).Equals(reqExt))
                    .OrderByDescending(f => f.Value)
                    .ThenBy(f => f.Key);
                if (filtered.Count() > 0)
                {
                    foreach (var file in filtered)
                    {
                        Console.WriteLine("{0} - {1} KB", file.Key.Substring(file.Key.LastIndexOf('\\') + 1), file.Value);
                    }
                }
                else
                {
                    Console.WriteLine("No");
                }

            }
            else
            {
                Console.WriteLine("No");
            }
        }
    }
}
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace p08_Commits
{
    class Commit
    {
        public string Hash { get; set; }
        public string Message { get; set; }
        public int Additions { get; set; }
        public int Deletions { get; set; }
    }
    class Commits
    {
        static void Main(string[] args)
        {
            SortedDictionary<string, SortedDictionary<string, List<Commit>>> commits = new 
                SortedDictionary<string, SortedDictionary<string, List<Commit>>>();
            string pattern = @"https:\/\/github\.com\/(?<user>[A-Za-z\d-]+)\/(?<repo>[A-Za-z-_]+)\/commit\/(?<hash>[a-f\d]{40}),(?<msg>[^\n]+),(?<add>\d+),(?<del>\d+)";
            string input;
            while (!"git push".Equals(input = Console.ReadLine()))
            {
                Match m = Regex.Match(input, pattern);
                if (!m.Success) continue;
                string user = m.Groups["user"].Value;
                string repo = m.Groups["repo"].Value;
                if (!commits.ContainsKey(user)) commits
                        .Add(user, new SortedDictionary<string, List<Commit>>());
                if (!commits[user].ContainsKey(repo)) commits[user].Add(repo, new List<Commit>());
                int adds, dels;
                if (!int.TryParse(m.Groups["add"].Value, out adds)) continue;
                if (!int.TryParse(m.Groups["del"].Value, out dels)) continue;
                if (commits[user][repo].Any(x => x.Hash.Equals(m.Groups["hash"].Value))) continue;
                commits[user][repo].Add(new Commit()
                    {
                        Hash = m.Groups["hash"].Value,
                        Message = m.Groups["msg"].Value,
                        Additions = adds,
                        Deletions = dels
                    });
            }
            foreach (string user in commits.Keys)
            {
                Console.WriteLine("{0}:", user);
                foreach (string repo in commits[user].Keys)
                {
                    Console.WriteLine("  {0}:", repo);
                    foreach (Commit c in commits[user][repo])
                    {
                        Console.WriteLine($"    commit {c.Hash}: {c.Message} ({c.Additions} additions, {c.Deletions} deletions)");
                    }
                    int totalAdds = commits[user][repo].Sum(x => x.Additions);
                    int totalDels = commits[user][repo].Sum(x => x.Deletions);
                    Console.WriteLine($"    Total: {totalAdds} additions, {totalDels} deletions");
                }

            }
        }
    }
}

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
            Regex pattern = new Regex(
                @"^https:\/\/github\.com\/(?'user'[a-zA-Z0-9\-]+)\/(?'repo'[a-zA-Z_-]+)\/commit\/(?'hash'[\da-fA-F]{40}),(?'msg'[^\n]+),(?'add'[0-9]+),(?'del'[0-9]+)$");
            string input;
            while (!"git push".Equals(input = Console.ReadLine()))
            {
                Match m = pattern.Match(input);
                if (!m.Success) continue;
                string user = m.Groups["user"].Value;
                string repo = m.Groups["repo"].Value;
                if (!commits.ContainsKey(user)) commits
                        .Add(user, new SortedDictionary<string, List<Commit>>());
                if (!commits[user].ContainsKey(repo)) commits[user].Add(repo, new List<Commit>());
                commits[user][repo].Add(new Commit()
                {
                    Hash = m.Groups["hash"].Value,
                    Message = m.Groups["msg"].Value,
                    Additions = int.Parse(m.Groups["add"].Value),
                    Deletions = int.Parse(m.Groups["del"].Value)
                });
            }
            foreach (string user in commits.Keys)
            {
                Console.WriteLine("{0}:", user);
                foreach (string repo in commits[user].Keys)
                {
                    int totalAdds = commits[user][repo].Sum(x => x.Additions);
                    int totalDels = commits[user][repo].Sum(x => x.Deletions);
                    Console.WriteLine("  {0}:", repo);
                    foreach (Commit c in commits[user][repo])
                    {
                        Console.WriteLine($"    commit {c.Hash}: {c.Message} ({c.Additions} additions, {c.Deletions} deletions)");
                    }
                    Console.WriteLine($"    Total: {totalAdds} additions, {totalDels} deletions");
                }
            }
        }
    }
}

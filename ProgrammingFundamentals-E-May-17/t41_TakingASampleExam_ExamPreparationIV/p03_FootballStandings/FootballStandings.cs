using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace p03_FootballStandings
{
    class Team
    {
        public string Name { get; set; }
        public long Goals { get; set; }
        public long Score { get; set; } 
    }
    class FootballStandings
    {
        static void Main(string[] args)
        {
            List<Team> teams = new List<Team>();
            string separator = Console.ReadLine();
            string input;
            while (!"final".Equals(input = Console.ReadLine()))
            {
                string[] tokens = input.Split();
                string teamA = ConvertTeam(tokens[0], separator);
                string teamB = ConvertTeam(tokens[1], separator);
                string[] goals = tokens[2].Split(new char[] { ':' }, StringSplitOptions.RemoveEmptyEntries);
                long goalsA = long.Parse(goals[0]);
                long goalsB = long.Parse(goals[1]);
                long scoreA = 0;
                long scoreB = 0;

                if (!teams.Any(x => x.Name.Equals(teamA)))
                    teams.Add(new Team() { Name = teamA });
                if (!teams.Any(x => x.Name.Equals(teamB)))
                    teams.Add(new Team() { Name = teamB });

                if (goalsA > goalsB) scoreA = 3;   
                else if (goalsA < goalsB) scoreB = 3; 
                else
                {
                    scoreA = 1;
                    scoreB = 1;
                }
                Team a = teams.FirstOrDefault(x => x.Name.Equals(teamA));
                Team b = teams.FirstOrDefault(x => x.Name.Equals(teamB));
                a.Goals += goalsA;
                a.Score += scoreA;
                b.Goals += goalsB;
                b.Score += scoreB;
            }
            int place = 1;
            Console.WriteLine("League standings:");
            foreach (Team team in teams.OrderByDescending(x => x.Score).ThenBy(x => x.Name))
            {
                Console.WriteLine($"{place}. {team.Name} {team.Score}");
                place++;
            }
            Console.WriteLine("Top 3 scored goals:");
            foreach (Team team in teams.OrderByDescending(x => x.Goals).ThenBy(x => x.Name).Take(3))
            {
                Console.WriteLine($"- {team.Name} -> {team.Goals}");
            }
        }

        private static string ConvertTeam(string v, string separator)
        {
            string team = "";
            int first = v.IndexOf(separator) + separator.Length;
            team = v.Substring(first);
            return ReverseString(team.Substring(0, team.IndexOf(separator)));
        }
        public static string ReverseString(string s)
        {
            char[] arr = s.ToCharArray();
            Array.Reverse(arr);
            return new string(arr).ToUpper();
        }
    }
}

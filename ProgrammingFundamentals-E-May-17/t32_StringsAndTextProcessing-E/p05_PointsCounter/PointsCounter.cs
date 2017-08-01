using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;


namespace p05_PointsCounter
{
    class Team
    {
        public string Name { get; set; }
        public List<Player> Players { get; set; }

        public Team(string input)
        {
            this.Name = input;
            this.Players = new List<Player>();
        }
   
        public void AddToTeam(string player, int points)
        {
            this.Players.Add(new Player(player, points));
        }

    }

    class Player
    {
        public string Name { get; set; }
        public int Points { get; set; }
        public Player(string name, int points)
        {
            this.Name = name;
            this.Points = points;
        }
    }
    class PointsCounter
    {
        static void Main(string[] args)
        {
            string input;
            Dictionary<string, Team> teams = new Dictionary<string, Team>();
            while (!"Result".Equals(input = Console.ReadLine()))
            {
                string[] tokens = ClearInput(input).Split('|');
                string team, player;
                if (IsTeam(tokens[0]))
                {
                    team = tokens[0];
                    player = tokens[1];
                }
                else
                {
                    team = tokens[1];
                    player = tokens[0];
                }
                int points = int.Parse(tokens[2]);
                if (!teams.ContainsKey(team))
                    teams.Add(team, new Team(team));
                if (!teams[team].Players.Exists(x => (x.Name).Equals(player)))
                    teams[team].Players.Add(new Player(player, points));
                else
                    teams[team].Players
                        .First(x => x.Name.Equals(player))
                        .Points = points;
            }

            foreach (var team in teams
                .OrderByDescending(x => x.Value.Players
                .Sum(p => p.Points)))
            {
                Console.WriteLine("{0} => {1}", team.Key, 
                    team.Value.Players.Sum(p => p.Points));
                Console.WriteLine("Most points scored by {0}", 
                    team.Value.Players.First(p => p.Points
                    .Equals(team.Value.Players
                    .Max(x => x.Points))).Name);
            }
            Console.WriteLine();
        }

        private static bool IsTeam(string v)
        {
            return !v.Any(x => x > 'Z');
        }

        private static string ClearInput(string input)
        {
            string pattern = @"([\%\@\*\$])";
            string substitution = @"";
            
            Regex regex = new Regex(pattern);
            string output = regex.Replace(input, substitution);
            return output;
        }
    }
}

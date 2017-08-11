using System;
using System.Collections.Generic;
using System.Linq;

namespace p04_SoftUniBeerPong
{
    class SoftUniBeerPong
    {
        static void Main(string[] args)
        {
            Dictionary<string, Dictionary<string, long>> teams = new Dictionary<string, Dictionary<string, long>>();

            string input = Console.ReadLine();
            while (!"stop the game".Equals(input))
            {
                string[] tokens = input.Split(new char[] { '|' }, StringSplitOptions.RemoveEmptyEntries);
                string player = tokens[0];
                string team = tokens[1];
                long points = long.Parse(tokens[2]);

                if (!teams.ContainsKey(team))
                    teams.Add(team, new Dictionary<string, long>() { [player] = points });
                else if (teams[team].Count < 3)
                    teams[team].Add(player, points);

                input = Console.ReadLine();
            }

            ushort count = 1;
            foreach (KeyValuePair<string, Dictionary<string, long>> team in teams
                .Where(x => x.Value.Count == 3)
                .OrderByDescending(x => x.Value.Sum(i => i.Value)))
            {
                Console.WriteLine("{0}. {1}; Players:", count ,team.Key);
                count++;
                foreach (KeyValuePair<string, long> player in team.Value
                    .OrderByDescending(x => x.Value))
                {
                    Console.WriteLine("###{0}: {1}", player.Key, player.Value);
                }
            }
        }
    }
}

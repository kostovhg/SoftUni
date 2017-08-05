using System;
using System.Collections.Generic;
using System.Linq;

namespace p06_OrderedBankingSystem
{
    class OrderedBankingSystem
    {
        static void Main(string[] args)
        {
            Dictionary<string, Dictionary<string, decimal>> data = new Dictionary<string, Dictionary<string, decimal>>();
            string input = Console.ReadLine();
            while (!"end".Equals(input))
            {
                string[] inputTokens = input.Split(new string[] { " -> " }, StringSplitOptions.RemoveEmptyEntries);
                string bank = inputTokens[0];
                string account = inputTokens[1];
                decimal balance = decimal.Parse(inputTokens[2]);

                if (!data.ContainsKey(bank))
                    data.Add(bank, new Dictionary<string, decimal>());
                if (!data[bank].ContainsKey(account))
                    data[bank].Add(account, 0);
                data[bank][account] += balance;

                input = Console.ReadLine();
            }

            foreach (KeyValuePair<string, Dictionary<string, decimal>> bankKVP in data
                .OrderByDescending(x => x.Value.Values.Sum())
                .ThenByDescending(x => x.Value.Values.Max())
                )
            {
                string bank = bankKVP.Key;
                Dictionary<string, decimal> accountsKVP = bankKVP.Value;
                foreach (KeyValuePair<string, decimal> accountKVP in accountsKVP
                    .OrderByDescending(x => x.Value))
                {
                    string account = accountKVP.Key;
                    decimal balance = accountKVP.Value;
                    Console.WriteLine("{0} -> {1} ({2})", account, balance, bank);
                }
            }
        }
    }
}

using System;
using System.Collections.Generic;
using System.Linq;

namespace p02_OptimizedBankingSystem
{
    class BankAccount
    {
        public string Name { get; set; }
        public string Bank { get; set; }
        public decimal Balance { get; set; }

        public BankAccount(string name, string bank, decimal balance)
        {
            this.Name = name;
            this.Bank = bank;
            this.Balance = balance;
        }

        public bool Exist
            (string name, string bank)
        {
            if (this == null)
                return false;
            else
            return String
                .Equals(this.Name, name, StringComparison.CurrentCulture) 
                &&
                String
                .Equals(this.Bank, bank, StringComparison.CurrentCulture);
        }

        public void AddToBalance(decimal balance)
        {
            this.Balance += balance;
        }
    }
    class OptimizedBankingSystem
    {
        static void Main(string[] args)
        {
            List<BankAccount> accounts = new List<BankAccount>();
            string input;
            while (!"end".Equals(input = Console.ReadLine()))
            {
                string[] tokens = input
                    .Split(new string[] { " | " },
                    StringSplitOptions.RemoveEmptyEntries);
                string name = tokens[1];
                string bank = tokens[0];
                decimal balance = decimal.Parse(tokens[2]);
                if(accounts.Any(x => x.Exist(name, bank)))
                {
                    accounts.Single(x => x.Exist(name, bank))
                        .AddToBalance(balance);
                }
                else
                {
                    accounts.Add(new BankAccount(name, bank, balance));
                }
            }
            foreach (BankAccount acc in accounts
                .OrderByDescending(x => x.Balance)
                .ThenBy(x => x.Bank.Length))
            {
                Console.WriteLine("{0} -> {1} ({2})", acc.Name, acc.Balance, acc.Bank);
            }
        }
    }
}

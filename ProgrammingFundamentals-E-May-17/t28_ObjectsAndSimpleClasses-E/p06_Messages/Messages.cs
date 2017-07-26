using System;
using System.Collections.Generic;
using System.Linq;

namespace p06_Messages
{
    class Message
    {
        public string Content { get; set; }
        public User Sender { get; set; }
        public Message(string msg, User sender)
        {
            this.Content = msg;
            this.Sender = sender;
        }
    }
    class User
    {
        public string Username { get; set; }
        public List<Message> ReceivedMessages { get; set; }

        public User(string name)
        {
            this.Username = name;
            this.ReceivedMessages = new List<Message>();
        }
    }
    class Messages
    {
        static void Main(string[] args)
        {
            Dictionary<string, User> users = new Dictionary<string, User>();
            string input;
            while (!"exit".Equals(input = Console.ReadLine()))
            {
                string[] tokens = input.Split();
                if (!"register".Equals(tokens[0]))
                {
                    string sender = tokens[0];
                    string recipient = tokens[2];
                    string message = tokens[3];
                    if (users.ContainsKey(sender) && users.ContainsKey(recipient)) 
                    {
                        users[recipient]
                            .ReceivedMessages
                            .Add(new Message(message, users[sender]));
                    }
                }
                else if(!users.Any(x => (tokens[1]).Equals(x.Key)))
                {
                    users.Add(tokens[1], new User(tokens[1]));
                }
            }
            input = Console.ReadLine();
            string firstUser = input.Split()[0];
            string secondUser = input.Split()[1];
            int firstUMsgCount = users[firstUser]
                .ReceivedMessages
                .Where(x => (secondUser).Equals(x.Sender.Username))
                .Count();
            int secUMsgCount = users[secondUser]
                .ReceivedMessages
                .Where(x => (firstUser).Equals(x.Sender.Username))
                .Count();
            int msgCount = Math.Max(firstUMsgCount, secUMsgCount);

            if (msgCount < 1)
            {
                Console.WriteLine("No messages");
            }
            else
            {
                for (int i = 0; i < msgCount; i++)
                {
                    if (i < secUMsgCount)
                    {
                        Console.WriteLine("{0}: {1}",
                            firstUser,
                            users[secondUser]
                            .ReceivedMessages
                            .Where(x => x.Sender.Username.Equals(firstUser))
                            .ElementAt(i)
                            .Content);
                    }
                    if (i < firstUMsgCount)
                    {
                        Console.WriteLine("{1} :{0}",
                            secondUser,
                            users[firstUser]
                            .ReceivedMessages
                            .Where(x => x.Sender.Username.Equals(secondUser))
                            .ElementAt(i)
                            .Content);
                    }
                }
            }
        }
    }
}

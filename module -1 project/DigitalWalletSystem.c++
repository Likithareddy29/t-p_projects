#include <iostream>
#include <map>
#include <vector>
#include <algorithm>
using namespace std;

string processTransaction(map<int, long>& userDetails, int fromUser, int toUser, long amountForTransaction) {
    auto userOne = userDetails.find(fromUser);
    auto userTwo = userDetails.find(toUser);

    if (userOne != userDetails.end() && userTwo != userDetails.end()) {
        if (userOne->second >= amountForTransaction) {
            userOne->second -= amountForTransaction;
            userTwo->second += amountForTransaction;
            return "Success";
        } else {
            return "Failure";
        }
    } else {
        return "Failure";
    }
}

void printSortedUsers(const map<int, long>& userDetails) {
    vector<pair<int, long>> sortedUsers(userDetails.begin(), userDetails.end());

    sort(sortedUsers.begin(), sortedUsers.end(), [](const pair<int, long>& a, const pair<int, long>& b) {
        if (a.second == b.second) {
            return a.first < b.first;
        }
        return a.second < b.second;
    });

    for (const auto& user : sortedUsers) {
        cout << user.first << " " << user.second << endl;
    }
}

int main() {
    int N;
    cin >> N;
    map<int, long> userDetails;
    for (int i = 0; i < N; i++) {
        int userId;
        long accountBalance;
        cin >> userId >> accountBalance;
        userDetails.insert({userId, accountBalance});
    }

    int T;
    cin >> T;
    for (int i = 0; i < T; i++) {
        int fromUser, toUser;
        long amountForTransaction;
        cin >> fromUser >> toUser >> amountForTransaction;

        string result = processTransaction(userDetails, fromUser, toUser, amountForTransaction);
        cout << result << endl;
    }
    cout<<"\n";
    printSortedUsers(userDetails);

    return 0;
}
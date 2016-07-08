#include <bits/stdc++.h>
#define MAX 50
typedef long long int ll;

using namespace std;

int m[MAX][MAX], rows, cols;
int d[4][2] = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
ll dp[MAX][MAX];
bool v[MAX][MAX];

ll solve(int r, int c) {
    ll sum = 0;
    if (v[r][c])
        return dp[r][c];
    v[r][c] = true;
    for (int z = 0; z<4; z++)
        if (r + d[z][0] >= 0 && r + d[z][0] < rows && c + d[z][1] >= 0
                && c + d[z][1] < cols && m[r][c] > m[r + d[z][0]][c + d[z][1]])
            sum += solve(r + d[z][0], c + d[z][1]);
    dp[r][c] = (sum>1)?sum:1;
    return dp[r][c];
}

void reset() {
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            dp[i][j] = m[i][j] = 0;
            v[i][j] = false;
        }

    }
}

int main(){
	int t;
    scanf("%d", &t);
    for (int i = 0; i < t; i++) {
        scanf("%d %d", &rows, &cols);
        reset();
        for (int j = 0; j < rows; j++)
            for (int k = 0; k < cols; k++)
                scanf("%d", &m[j][k]);
        ll ans = 0;
        bool start = true;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                start = true;
                if (!v[r][c]) {
                    for (int z = 0; z<4; z++)
                        if (r + d[z][0] >= 0 && r + d[z][0] < rows && c + d[z][1] >= 0
                                && c + d[z][1] < cols && m[r][c] < m[r + d[z][0]][c + d[z][1]])
                            start = false;
                    if (start)
                        ans += solve(r, c);
                }
            }
        }
        printf("Case #%d: %llu\n",i+1, ans);
    }
}


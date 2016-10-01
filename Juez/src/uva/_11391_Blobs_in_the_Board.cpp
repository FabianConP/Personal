#include<bits/stdc++.h>

using namespace std;

int dr [] = {-1, -1, 0, 1,  1,  1,  0, -1};
int dc [] = { 0,  1, 1, 1,  0, -1, -1, -1};

int r, c, n;

void getRowCol(int pos, int &row, int &col){
    row = (pos / c);
    col = (pos % c);
}

int pos(int row, int col){
    return (row * c + col);
}

bool blobIn(int row, int col, int m){
    return (m >> pos(row, col)) & 1;
}

bool correct(int row, int col, int m, int dx, int dy){
    if(dx * 2 + row < 0 || dx * 2 + row >= r) return false;
    if(dy * 2 + col < 0 || dy * 2 + col >= c) return false;
    if(!blobIn(row + dx, col + dy, m)) return false;
    if(blobIn(row + dx * 2, col + dy * 2, m)) return false;
    return true;
}

int setBit(int pos, int value, int m){
    if(value) return m | (1 << pos);
    return m ^ (1 << pos);
}

int makeMove(int row, int col, int m, int dx, int dy){
    m = setBit(pos(row, col), 0, m);
    m = setBit(pos(row + dx, col + dy), 0, m);
    m = setBit(pos(row + dx * 2, col + dy * 2), 1, m);
    return m;
}

int dp[16][1 << 16];

int solve(int moves, int m, int cnt){
    if(moves == (n - 1))
        return (cnt == 1) ? 1 : 0;

    if(dp[moves][m] != -1) return dp[moves][m];

    int ways = 0, lim = r * c, row, col;

    for(int i = 0; i < lim ; i++){
        getRowCol(i, row, col);
        if(blobIn(row, col, m))
            for(int j = 0; j < 8; j++)
                if(correct(row, col, m, dr[j], dc[j]))
                    ways += solve(moves + 1, makeMove(row, col, m, dr[j], dc[j]), cnt - 1);
    }


    return (dp[moves][m] = ways);
}

int main(){

    //freopen("entrada", "r+", stdin);

    ios_base::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    int ncases, x, y, m;

    cin >> ncases;

    for(int ncase = 1; ncase <= ncases; ncase++){
        cin >> r >> c >> n;

        m = 0;
        for(int i = 0; i < n; i++){
            cin >> x >> y;
            m = setBit(pos(x - 1, y - 1), 1, m);
        }

        memset(dp, -1, sizeof(dp));

        cout << "Case " << ncase << ": " << solve(0, m, n) << endl;
    }

    return 0;
}

#include<bits/stdc++.h>

using namespace std;

#define REP(i, n) for (int i = 0; i < (int)(n); ++i)

const int MAXN = 100010;
string S, Q;
int N, gap;
int sa[MAXN], ra[MAXN], tmp[MAXN], lcp[MAXN];

bool sufCmp(int i, int j){
    if (ra[i] != ra[j])
        return ra[i] < ra[j];
    i += gap;
    j += gap;
    return (i < N && j < N) ? ra[i] < ra[j] : i > j;
}

void buildSA(){
    N = S.size();
    REP(i, N) sa[i] = i, ra[i] = S[i];
    for (gap = 1;; gap *= 2){
        sort(sa, sa + N, sufCmp);
        REP(i, N - 1) tmp[i + 1] = tmp[i] + sufCmp(sa[i], sa[i + 1]);
        REP(i, N) ra[sa[i]] = tmp[i];
        if (tmp[N - 1] == N - 1) break;
    }
}

bool matching(){
    int n  = S.size(), m = Q.size();
    int low = 0, high = n - 1, mid, l = -1, h = -1, r;
    while(low <= high){
        mid = (low + high) / 2;
        r = S.compare(sa[mid], m, Q);
        if(r >= 0){
            high = mid - 1;
            if(r == 0) l = mid;
        }else
            low = mid + 1;
    }
    if(l < 0 ) return false;
    low = 0, high = n - 1;
    while(low <= high){
        mid = (low + high) / 2;
        r = S.compare(sa[mid], m, Q);
        if(r <= 0){
            low = mid + 1;
            if(r == 0) h = mid;
        }else
            high = mid - 1;
    }
    return h >= 0 && l <= h;
}

int main(){

    ios_base::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    int ncases, queries;

    cin >> ncases;

    REP(ncase, ncases){
        cin >> S;
        buildSA();
        cin >> queries;
        REP(i, queries){
            cin >> Q;
            if(matching()) cout << "y\n";
            else cout << "n\n";
        }
    }

    return 0;
}

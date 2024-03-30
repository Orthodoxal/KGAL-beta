package genetic.ga.cellular.utils

/**
 * @see <a href="https://en.wikipedia.org/wiki/Delannoy_number">Delannoy number</a>
 */
fun delannoyNumber(m: Int, n: Int): Int {
    val dp = Array(m + 1) { IntArray(n + 1) }

    for (i in 0..m) {
        for (j in 0..n) {
            if (i == 0 || j == 0) {
                dp[i][j] = 1
            } else {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] + dp[i - 1][j - 1]
            }
        }
    }

    return dp[m][n]
}

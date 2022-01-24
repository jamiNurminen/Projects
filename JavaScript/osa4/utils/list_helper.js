const totalLikes = (blogs) => {
    if (blogs.length === 0) return 0
    let sum = 0
    for (let i = 0; i < blogs.length; i++) {
        sum = sum + blogs[i].likes
    }
    return sum
}

const favoriteBlog = (blogs) => {

}

module.exports = {
    totalLikes,
    favoriteBlog
}
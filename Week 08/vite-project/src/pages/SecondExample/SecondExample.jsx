import {useState} from 'react'

const movieList = [
    {'title': 'Cat Coneheads', 'length': 112, 'category': 'Sci-Fi'},
    {'title': 'Driver Annie', 'length': 159, 'category': 'Comedy'},
    {'title': 'Gold River', 'length': 154, 'category': 'Music'},
    {'title': 'River Outlaw', 'length': 149, 'category': 'Documentary'},
    {'title': 'Bowfinger Gables', 'length': 72, 'category': 'Sci-Fi'}
]

const categories = ['All', 'Sci-Fi', 'Comedy', 'Music', 'Documentary']

const SecondExample = () => {
    const [filters, setFilters] = useState({title: '', category: 'All'})

    const onTitleChange = title => setFilters(prevFilters => ({...prevFilters, title}))
    const onCategoryChange = category => setFilters(prevFilters => ({...prevFilters, category}))

    const filterByTitle = movie => movie.title.toLowerCase().includes(filters.title.toLowerCase())
    const filterByCategory = movie => filters.category === 'All' || movie.category === filters.category

    const filteredMovies = movieList
        .filter(filterByTitle)
        .filter(filterByCategory)

    return <div>
        <h1>Search</h1>
        <input type="text" value={filters.title} onChange={e => onTitleChange(e.target.value)}/>
        <select value={filters.category} onChange={e => onCategoryChange(e.target.value)}>
            {categories.map(category => {
                return <option key={category} value={category}>{category}</option>
            })}
        </select>
        <ul>
            {filteredMovies.map(
                (value, key) => <li key={key}>{value.title}</li>
            )}
        </ul>
    </div>

}

export default SecondExample
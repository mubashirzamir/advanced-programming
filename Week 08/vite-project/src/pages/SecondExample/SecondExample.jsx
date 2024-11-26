import {useEffect, useState} from 'react'
import Select from '../../components/Select/Select.jsx'

const movieList = [
    {'title': 'Cat Coneheads', 'length': 112, 'category': 'Sci-Fi', 'language': 'English'},
    {'title': 'Driver Annie', 'length': 159, 'category': 'Comedy', 'language': 'Spanish'},
    {'title': 'Gold River', 'length': 154, 'category': 'Music', 'language': 'English'},
    {'title': 'River Outlaw', 'length': 149, 'category': 'Documentary', 'language': 'English'},
    {'title': 'Bowfinger Gables', 'length': 72, 'category': 'Sci-Fi', 'language': 'English'}
]

const categories = ['All', 'Sci-Fi', 'Comedy', 'Music', 'Documentary']

const languages = ['All', 'English', 'Spanish']

const SecondExample = () => {
    const [movies, setMovies] = useState([])
    const [loading, setLoading] = useState(true)
    const [error, setError] = useState(null)
    const [filters, setFilters] = useState({title: '', category: 'All', language: 'All'})


    useEffect(() => {
        const fetchMovies = () => {
            fetchMoviesService().then(setMovies)
                .catch(setError)
                .finally(() => setLoading(false))
        }

        fetchMovies()
    }, [])

    const fetchMoviesService = () => {
        return new Promise((resolve, reject) => {
            setTimeout(() => Math.random() > 0.5 ? reject('Network error.') : resolve(movieList), 100)
        })
    }

    const onTitleChange = title => setFilters(prevFilters => ({...prevFilters, title}))
    const onCategoryChange = category => setFilters(prevFilters => ({...prevFilters, category}))
    const onLanguageChange = language => setFilters(prevFilters => ({...prevFilters, language}))

    const filterByTitle = movie => movie.title.toLowerCase().includes(filters.title.toLowerCase())
    const filterByCategory = movie => filters.category === 'All' || movie.category === filters.category
    const filterByLanguage = movie => filters.language === 'All' || movie.language === filters.language

    const filteredMovies = movies
        .filter(filterByTitle)
        .filter(filterByCategory)
        .filter(filterByLanguage)

    if (loading) return <div>Loading...</div>
    if (error) return <div>Error: {error}</div>

    return <div>
        <h1>Search</h1>
        <input type="text" value={filters.title} onChange={e => onTitleChange(e.target.value)}/>
        <Select options={categories} value={filters.category} onChange={onCategoryChange}/>
        <Select options={languages} value={filters.language} onChange={onLanguageChange}/>
        <ul>
            {filteredMovies.map(
                (value, key) => <li key={key}>{value.title}</li>
            )}
        </ul>
    </div>

}

export default SecondExample
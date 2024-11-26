import PropTypes from 'prop-types'

const Film = ({title, description, rating, category}) => {
    return <div className="container">
        <h1>{title}</h1>
        <ul>
            <li>Description: {description}</li>
            <li>Category: {category}</li>
            <li>Rating: {rating}</li>
        </ul>
    </div>
}

Film.propTypes = {
    title: PropTypes.string.isRequired,
    description: PropTypes.string.isRequired,
    rating: PropTypes.string.isRequired,
    category: PropTypes.string.isRequired
}

export default Film
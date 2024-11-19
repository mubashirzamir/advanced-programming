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

export default Film
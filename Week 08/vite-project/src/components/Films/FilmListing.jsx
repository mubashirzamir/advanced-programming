import Film from './Film.jsx'

const FilmListing = ({films}) => {
    return <div>
        <h1>Film Listing</h1>
        {films.map(film => {
            return <Film key={film.id} {...film}/>
        })}
    </div>
}

export default FilmListing
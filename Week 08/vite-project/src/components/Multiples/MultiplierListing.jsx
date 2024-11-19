import Multiplier from './Multiplier.jsx'

const MultiplierListing = ({multiples}) => {
    return <div>
        <h1>Multiplier Listing</h1>
        {multiples.map(multiple => {
            return <Multiplier key={multiple} multiple={multiple}/>
        })}
    </div>
}

export default MultiplierListing
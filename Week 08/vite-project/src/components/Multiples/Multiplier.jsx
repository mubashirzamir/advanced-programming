const Multiplier = ({multiple}) => {
    const xTimesY = (x, y) => x * y

    return <div className="container">
        <h1>{multiple} Times Table</h1>
        <ol>
            {Array.from({length: 10}, (_, i) => (
                <li key={i + 1}>{multiple} x {i + 1} = {xTimesY(multiple, i + 1)}</li>
            ))}
        </ol>
    </div>
}

export default Multiplier
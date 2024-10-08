package genetic.ga.panmictic.operators.crossover

sealed interface CrossoverType {
    data object Iterative : CrossoverType // итеративное скрещивание, гарантировано скрещивание всех особей попарно (кроме центральной особи при нечетном размере популяции), элитные особи скрещиваются, но не изменяются, более производительный чем случайный тип, менее эффективен
    data object Randomly : CrossoverType // Создает промежуточную копию популяции, в цикле производит n скрещиваний, где n = (size - elitism) / 2, особи выбираются случайно из предыдущей популяции, перед скрещиванием выбранные особи клонируются, новое поколение заполняет промежуточную популяцию, после чего промежуточная популяцию заменяет предыдущую, менее производительный чем итеративный тип, более эффективен
}

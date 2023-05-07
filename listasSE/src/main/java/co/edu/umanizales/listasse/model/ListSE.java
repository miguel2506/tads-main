package co.edu.umanizales.listasse.model;

import co.edu.umanizales.listasse.controller.dto.ReportKidsDTO;
import co.edu.umanizales.listasse.exception.ListSEException;
import lombok.Data;

@Data
public class ListSE {
    private Node head;
    private int size;

    /**
     *El método add recibe un objeto Kid llamado kid como parámetro y lanza una excepción del tipo ListSEException

     * Comienza verificando si el nodo inicial (head) de la lista enlazada no es nulo. Esto se hace para comprobar si la lista ya contiene elementos.

     * Si la lista no está vacía, se crea un nuevo nodo temporal (temp) y se inicializa con el nodo inicial (head) de la lista. Esto se hace para recorrer
     la lista y encontrar el último nodo.

     * Se inicia un bucle while que continúa hasta que se alcance el último nodo de la lista (temp.getNext() != null).

     * Dentro del bucle, se verifica si la identificación del niño (kid.getIdentification()) es igual a la identificación del niño almacenado en el nodo actual
     (temp.getData().getIdentification()). Si son iguales, significa que ya existe un niño con la misma identificación en la lista. En ese caso, se lanza una excepción
     ListSEException con el mensaje "Ya existe un niño".

     * Después de la verificación, el puntero temporal (temp) se mueve al siguiente nodo en la lista (temp = temp.getNext()).

     * Después de salir del bucle while, significa que se ha llegado al último nodo de la lista. Se realiza una verificación adicional para asegurarse de que la identificación
     del niño no exista en el último nodo. Si existe, se lanza la excepción ListSEException de nuevo.

     * Si la verificación es exitosa, se crea un nuevo nodo (newNode) utilizando el objeto kid proporcionado y se establece como el siguiente nodo del último nodo de la lista
     (temp.setNext(newNode)). Esto agrega el nuevo nodo al final de la lista.

     * Si la lista estaba vacía inicialmente (es decir, el nodo inicial era nulo), se crea un nuevo nodo utilizando el objeto kid y se establece como el nodo inicial (head)
     de la lista.

     * Finalmente, se incrementa el tamaño de la lista en 1 (size++).

     Este método recorre una lista enlazada simple para verificar si ya existe un niño con la misma identificación antes de agregar un nuevo niño al final de la lista.
     Si se encuentra un niño con la misma identificación, se lanza una excepción. Si la lista está vacía, el nuevo niño se agrega como el primer nodo de la lista.

     */
    public void add(Kid kid) throws ListSEException {
        if (head != null) {
            Node temp = head;
            while (temp.getNext() != null) {
                if (temp.getData().getIdentification().equals(kid.getIdentification())) {
                    throw new ListSEException("Ya existe un niño");
                }
                temp = temp.getNext();

            }
            if (temp.getData().getIdentification().equals(kid.getIdentification())) {
                throw new ListSEException("Ya existe un niño");
            }
            /// Parado en el último
            Node newNode = new Node(kid);
            temp.setNext(newNode);
        } else {
            head = new Node(kid);
        }
        size++;
    }

    /**
     * -Ejercicio número 1: Invertir lista.
     * Explicación:
     * El método invert verifica si el nodo inicial (head) de la lista no es nulo. Esto se hace para comprobar si la lista contiene elementos.
     * Si la lista no está vacía, se crea una nueva instancia de ListSE llamada listCP. Esta lista se utilizará para construir la lista invertida.
     * Se crea un nodo temporal (temp) y se inicializa con el nodo inicial (head) de la lista original.
     * Se inicia un bucle while que continúa hasta que el nodo temporal (temp) sea nulo. Esto significa que se ha llegado al final de la lista original.
     * Dentro del bucle, se agrega el dato almacenado en el nodo temporal a la lista listCP utilizando el método addToStart. Esto asegura que los elementos
     se agreguen al principio de la lista listCP, lo que resultará en la inversión del orden.
     * Después de agregar el dato a listCP, se mueve al siguiente nodo en la lista original asignando el nodo siguiente de temp a temp.
     * Después de salir del bucle while, significa que se ha construido la lista invertida en listCP.
     * Finalmente, se actualiza el nodo inicial (head) de la lista original con el nodo inicial de la lista invertida (listCP.getHead()). Esto hace que la lista original ahora contenga los elementos en orden inverso.
     * Si la lista original estaba vacía inicialmente (es decir, el nodo inicial era nulo), se lanza una excepción ListSEException con el mensaje "La lista está vacía".
     */
    public void invert() throws ListSEException {
        if (this.head != null) {
            ListSE listCP = new ListSE();
            Node temp = this.head;
            while (temp != null) {
                listCP.addToStart(temp.getData());
                temp = temp.getNext();
            }
            this.head = listCP.getHead();
        } else {
            throw new ListSEException("La lista está vacía");
        }
    }

    /**
     * -Ejercicio número 2: Niños al inicio y niñas al final.
     * Explicación:
     *El método orderBoysToStart verifica si el nodo inicial (head) de la lista no es nulo. Esto se hace para comprobar si la lista contiene elementos.
     * Si la lista no está vacía, se crea una nueva instancia de ListSE llamada listCp. Esta lista se utilizará para construir la lista ordenada.
     * Se crea un nodo temporal (temp) y se inicializa con el nodo inicial (head) de la lista original.
     * Se inicia un bucle while que continúa hasta que el nodo temporal (temp) sea nulo. Esto significa que se ha llegado al final de la lista original.
     * Dentro del bucle, se verifica el género del niño almacenado en el nodo temporal. Si el género es masculino ('M'), se agrega el dato al principio de
     listCp utilizando el método addToStart.
     * Si el género del niño no es masculino, se agrega el dato al final de listCp utilizando el método add. Esto mantiene el orden de los niños no masculinos tal
     como estaban en la lista original.
     * Después de agregar el dato a listCp, se mueve al siguiente nodo en la lista original asignando el nodo siguiente de temp a temp.
     * Después de salir del bucle while, significa que se ha construido la lista ordenada en listCp.
     * Finalmente, se actualiza el nodo inicial (head) de la lista original con el nodo inicial de la lista ordenada (listCp.getHead()). Esto hace que la
     lista original ahora contenga los niños de género masculino al principio, seguidos de los niños de otros géneros en el orden original.
     * Si la lista original estaba vacía inicialmente (es decir, el nodo inicial era nulo), se lanza una excepción ListSEException con el mensaje "La lista está vacía".
     */
    public void orderBoysToStart() throws ListSEException {
        if (this.head != null) {
            ListSE listCp = new ListSE();
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getGender() == 'M') {
                    listCp.addToStart(temp.getData());
                } else {
                    listCp.add(temp.getData());
                }

                temp = temp.getNext();
            }
            this.head = listCp.getHead();
        } else {
            throw new ListSEException("La lista está vacía");
        }
    }

    /**
     * -Ejercicio número 3: Intercalar niño, niña, niño, niña
     * Explicación:
     *Se crean dos nuevas instancias de ListSE llamadas listBoy y listGirl. Estas listas se utilizarán para almacenar los niños de género masculino y femenino, respectivamente.
     * Se crea un nodo temporal (temp) y se inicializa con el nodo inicial (head) de la lista original.
     * Se verifica si el nodo temporal es nulo. Si es nulo, significa que la lista original está vacía y se lanza una excepción ListSEException con el mensaje "La lista está vacía".
     * Se inicia un bucle while que continúa hasta que el nodo temporal sea nulo. Esto significa que se ha llegado al final de la lista original.
     * Dentro del bucle, se verifica el género del niño almacenado en el nodo temporal. Si el género es masculino ('M'), se agrega el dato a listBoy utilizando el método add.
     Si el género es femenino ('F'), se agrega el dato a listGirl.
     * Después de agregar el dato a la lista correspondiente, se mueve al siguiente nodo en la lista original asignando el nodo siguiente de temp a temp.
     * Después de salir del bucle while, significa que se han construido las listas listBoy y listGirl que contienen los niños de género masculino y femenino, respectivamente.
     * Se crea una nueva instancia de ListSE llamada newListBoysGirls. Esta lista se utilizará para construir la lista final intercalando los niños de género masculino y femenino.
     * Se crean dos nodos (boyNode y girlNode) y se inicializan con los nodos iniciales de listBoy y listGirl, respectivamente.
     * Se inicia un bucle while que continúa mientras boyNode o girlNode no sean nulos. Esto significa que todavía hay niños en al menos una de las listas.
     * Dentro del bucle, se verifica si boyNode no es nulo. Si es así, se agrega el dato del nodo a newListBoysGirls utilizando el método add, y luego se mueve al siguiente
     nodo en listBoy asignando el nodo siguiente de boyNode a boyNode.
     * Se verifica si girlNode no es nulo. Si es así, se agrega el dato del nodo a newListBoysGirls, y luego se mueve al siguiente nodo en listGirl asignando el nodo siguiente
     de girlNode a girlNode.
     * Después de salir del bucle while, significa que se ha construido la lista final intercalada en newListBoysGirls.
     * Finalmente, se actualiza el nodo inicial (head) de la lista original con el nodo inicial de la lista intercalada (newListBoysGirls.getHead()).
     Esto hace que la lista original contenga los niños de género masculino y femenino intercalados.
     */
    public void intercalateBoysGirls() throws ListSEException {
        ListSE listBoy = new ListSE();
        ListSE listGirl = new ListSE();
        Node temp = this.head;

        if (temp == null) {
            throw new ListSEException("La lista está vacía");
        }

        while (temp != null) {
            if (temp.getData().getGender() == 'M') {
                listBoy.add(temp.getData());
            }
            if (temp.getData().getGender() == 'F') {
                listGirl.add(temp.getData());
            }
            temp = temp.getNext();
        }
        ListSE newListBoysGirls = new ListSE();
        Node boyNode = listBoy.getHead();
        Node girlNode = listGirl.getHead();
        while (boyNode != null || girlNode != null) {
            if (boyNode != null) {
                newListBoysGirls.add(boyNode.getData());
                boyNode = boyNode.getNext();
            }
            if (girlNode != null) {
                newListBoysGirls.add(girlNode.getData());
                girlNode = girlNode.getNext();
            }
        }
        this.head = newListBoysGirls.getHead();
    }

    /**
     * -Ejercicio número 4: Dada una edad eliminar a los niños de la edad dada
     * Explicación:
     *El método deleteByAge verifica si el nodo inicial (head) de la lista no es nulo. Esto se hace para comprobar si la lista contiene elementos.
     * Si la lista no está vacía, se verifica si el dato almacenado en el nodo inicial (head) tiene la misma edad que el valor proporcionado (age). Si es así, se actualiza el nodo inicial para que apunte al siguiente nodo (this.head = this.head.getNext()). Esto elimina el primer nodo de la lista.
     * Si el dato de edad no se encuentra en el nodo inicial, se continúa con el siguiente paso.
     * Se crea un nodo temporal (temp) y se inicializa con el nodo inicial (head) de la lista original.
     * Se inicia un bucle while que continúa hasta que el nodo temporal (temp) sea nulo. Esto significa que se ha llegado al final de la lista original.
     * Dentro del bucle, se verifica si el siguiente nodo de temp no es nulo (temp.getNext() != null) y si el dato de edad del siguiente nodo es igual al valor proporcionado (temp.getNext().getData().getAge() == age). Esto se hace para encontrar el nodo que contiene el dato de edad que se desea eliminar.
     * Si se encuentra el nodo, se sale del bucle (break). Esto se hace para detener el bucle y mantener una referencia al nodo anterior al nodo que se desea eliminar.
     * Después de salir del bucle, se verifica si se encontró el nodo que contiene el dato de edad. Si temp no es nulo, significa que se encontró el nodo y se puede eliminar.
     * Para eliminar el nodo, se actualiza el enlace del nodo anterior para saltar el nodo que se desea eliminar (temp.setNext(temp.getNext().getNext())).
     * Si temp es nulo, significa que no se encontró un nodo con el dato de edad proporcionado en la lista. En este caso, se lanza una excepción ListSEException con un mensaje indicando que la edad no existe en la lista.
     * Si la lista original estaba vacía inicialmente (es decir, el nodo inicial era nulo), se lanza una excepción ListSEException con el mensaje "No hay datos en la lista".
     */
    public void deleteByAge(byte age) throws ListSEException {
        if (this.head != null) {
            if (this.head.getData().getAge() == age) {
                this.head = this.head.getNext();
            } else {
                Node temp = this.head;
                while (temp != null) {
                    if (temp.getNext() != null && temp.getNext().getData().getAge() == age) {
                        break;
                    }
                    temp = temp.getNext();
                }
                if (temp != null) {
                    temp.setNext(temp.getNext().getNext());
                } else {
                    throw new ListSEException("La edad " + age + " no existe en la lista");
                }
            }
        } else {
            throw new ListSEException("No hay datos en la lista");
        }
    }

    /**
     * -Ejercicio 5: Obtener el promedio de edad de los niños de la lista
     * Explicación:
     *El método averageAge verifica si el nodo inicial (head) de la lista no es nulo. Esto se hace para comprobar si la lista contiene elementos.
     * Si la lista no está vacía, se crea un nodo temporal (temp) y se inicializa con el nodo inicial (head) de la lista original.
     * Se crea una variable count y se inicializa en 0. Esta variable se utilizará para contar la cantidad de elementos en la lista.
     * Se crea una variable ages y se inicializa en 0. Esta variable se utilizará para almacenar la suma de las edades de los elementos en la lista.
     * Se inicia un bucle while que continúa hasta que el siguiente nodo de temp sea nulo (temp.getNext() != null). Esto significa que se ha llegado al final de la lista original.
     * Dentro del bucle, se incrementa la variable count en 1 para contar el elemento actual.
     * Se suma la edad del elemento actual (temp.getData().getAge()) a la variable ages.
     * Se actualiza el nodo temporal para que apunte al siguiente nodo (temp = temp.getNext()).
     * Después de salir del bucle, se ha recorrido toda la lista y se ha contado la cantidad de elementos y sumado las edades.
     * Se calcula el promedio de las edades dividiendo la suma de las edades (ages) entre la cantidad de elementos (count). Se utiliza un casting a float para asegurarse de obtener un resultado con decimales.
     * El promedio de las edades se devuelve como resultado del método.
     * Si la lista original estaba vacía inicialmente (es decir, el nodo inicial era nulo), se lanza una excepción ListSEException con el mensaje "La lista está vacía".
     */
    public float averageAge() throws ListSEException {
        if (head != null) {
            Node temp = head;
            int count = 0;
            int ages = 0;
            while (temp.getNext() != null) {
                count++;
                ages = ages + temp.getData().getAge();
                temp = temp.getNext();
            }
            return (float) ages / count;
        } else {
            throw new ListSEException("La lista está vacía");
        }
    }

    /**
     * Ejercicio 6: Generar un reporte que me diga cuantos niños hay de cada ciudad.
     * El método getCountKidsByLocationCode inicializa una variable count en 0. Esta variable se utilizará para contar la cantidad de niños que coinciden con el código de ubicación.
     * El método verifica si el nodo inicial (head) de la lista no es nulo. Esto se hace para comprobar si la lista contiene elementos.
     * Si la lista no está vacía, se crea un nodo temporal (temp) y se inicializa con el nodo inicial (head) de la lista original.
     * Se inicia un bucle while que continúa hasta que el nodo temporal (temp) sea nulo. Esto significa que se ha llegado al final de la lista original.
     * Dentro del bucle, se verifica si el código de ubicación del dato almacenado en el nodo temporal coincide con el código proporcionado (temp.getData().getLocation().getCode().equals(code)).
     * Si los códigos de ubicación coinciden, se incrementa la variable count en 1.
     * Se actualiza el nodo temporal para que apunte al siguiente nodo (temp = temp.getNext()).
     * Después de salir del bucle, se ha recorrido toda la lista y se ha contado la cantidad de niños cuyo código de ubicación coincide con el proporcionado.
     * Se devuelve el valor de la variable count, que representa la cantidad de niños con el código de ubicación específico.
     * Si la lista original estaba vacía inicialmente (es decir, el nodo inicial era nulo), se lanza una excepción ListSEException con el mensaje "La lista está vacía".
     */
    public int getCountKidsByLocationCode(String code) throws ListSEException {
        int count = 0;
        if (this.head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
            return count;
        } else {
            throw new ListSEException("La lista está vacía");
        }
    }

    public int getCountKidsByDepartmentCode(String code) throws ListSEException {
        int count = 0;
        if (this.head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getLocation().getCode().equals(code)) {
                    count++;
                }
                temp = temp.getNext();
            }
            return count;
        } else {
            throw new ListSEException("La lista está vacía");
        }
    }

    /**
     * El método getReportKidsByLocationGendersByAge toma dos parámetros: age, que representa la edad límite para incluir a los niños en el informe, y report, que es un objeto de tipo ReportKidsDTO utilizado para almacenar y actualizar el informe.
     * El método verifica si el nodo inicial (head) de la lista no es nulo. Esto se hace para comprobar si la lista contiene elementos.
     * Si la lista no está vacía, se crea un nodo temporal (temp) y se inicializa con el nodo inicial (head) de la lista original.
     * Se inicia un bucle while que continúa hasta que el nodo temporal (temp) sea nulo. Esto significa que se ha llegado al final de la lista original.
     * Dentro del bucle, se verifica si la edad del dato almacenado en el nodo temporal es mayor que la edad proporcionada (temp.getData().getAge() > age).
     * Si la edad cumple la condición, se llama al método updateQuantity del objeto report para actualizar la cantidad de niños en el informe. Se pasa el nombre de la ubicación del niño (temp.getData().getLocation().getName()) y su género (temp.getData().getGender()) como parámetros.
     * Se actualiza el nodo temporal para que apunte al siguiente nodo (temp = temp.getNext()).
     * Después de salir del bucle, se ha recorrido toda la lista y se ha actualizado el informe con la cantidad de niños que cumplen con la condición de edad.
     *
     */
    public void getReportKidsByLocationGendersByAge(byte age, ReportKidsDTO report) {
        if (head != null) {
            Node temp = this.head;
            while (temp != null) {
                if (temp.getData().getAge() > age) {
                    report.updateQuantity(temp.getData().getLocation().getName(),
                            temp.getData().getGender());
                }
                temp = temp.getNext();
            }
        }
    }

    /**
     * -Ejercicio 7: Método que me permita decirle a un niño determinado que adelante un número de posiciones dadas
     * Explicación:
     *El método passByPosition toma dos parámetros: identification, que es una identificación utilizada para identificar el elemento que se desea mover, y positions, que es el número de posiciones a las que se moverá el elemento.
     * El método verifica si el nodo inicial (head) de la lista no es nulo. Esto se hace para comprobar si la lista contiene elementos.
     * Si la lista no está vacía, se verifica si el número de posiciones es menor que el tamaño de la lista (positions < size). Si no se cumple esta condición, se lanza una excepción ListSEException con el mensaje "La posición ingresada es mayor o igual al tamaño de la lista".
     * Si la identificación del primer elemento (head.getData().getIdentification()) coincide con la identificación proporcionada, no se realiza ningún movimiento. Esto se debe a que el elemento ya está en la posición correcta.
     * En caso contrario, se inicializa una variable count en 1 y se crea un nodo temporal (temp) que apunta al nodo inicial (head) de la lista original.
     * Se inicia un bucle while que continúa hasta que la identificación del siguiente dato (temp.getNext().getData().getIdentification()) coincida con la identificación proporcionada. Esto se hace para encontrar el nodo que contiene la identificación que se desea mover.
     * Dentro del bucle, se actualiza el nodo temporal para que apunte al siguiente nodo (temp = temp.getNext()) y se incrementa la variable count en 1 para realizar un seguimiento de la posición actual.
     * Después de salir del bucle, se ha encontrado el nodo que contiene la identificación que se desea mover.
     * Se crea un nuevo nodo (temp2) con los datos del nodo que se desea mover (temp.getNext().getData()).
     * Se modifica la estructura de la lista para mover el nodo. Primero, se establece el enlace del nodo actual (temp) para saltar al siguiente nodo (temp.getNext().getNext()) y omitir el nodo que se está moviendo.
     * Luego, se verifica si la cantidad de posiciones a mover es mayor o igual que la posición actual más 1 (positions >= count + 1). Si es así, se llama al método addToStart para agregar el nodo movido al inicio de la lista. De lo contrario, se llama al método addByPosition para agregar el nodo movido a una posición calculada.
     * Si la lista original estaba vacía inicialmente (es decir, el nodo inicial era nulo), se lanza una excepción ListSEException con el mensaje "La lista se encuentra vacía".
     *
     */
    public void passByPosition(String identification, int positions) throws ListSEException {
        if (head != null) {
            if (positions < size) {
                if (head.getData().getIdentification() == identification) {

                } else {
                    int count = 1;
                    Node temp = head;
                    while (temp.getNext().getData().getIdentification() != identification) {
                        temp = temp.getNext();
                        count++;
                        if (temp.getNext() != null) {
                            return;
                        }
                    }
                    Node temp2 = new Node(temp.getNext().getData());
                    temp.setNext(temp.getNext().getNext());
                    if (positions >= count + 1) {
                        addToStart(temp2.getData());
                    } else {
                        addByPosition((count + 1) - positions, temp2.getData());
                    }
                }
            } else {
                throw new ListSEException("La posición ingresada es mayor o igual al tamaño de la lista.");
            }
        } else {
            throw new ListSEException("La lista se encuentra vacía.");
        }
    }

    /**
     * -Ejercicio 8: Método que me permita decirle a un niño determinado que pierda un numero de posiciones dadas
     * Explicación:
     *El método afterwardsPositions toma dos parámetros: identification, que es una identificación utilizada para identificar el elemento que se desea mover, y positions, que es el número de posiciones a las que se moverá el elemento hacia adelante.
     * El método verifica si el nodo inicial (head) de la lista no es nulo. Esto se hace para comprobar si la lista contiene elementos.
     * Si la lista no está vacía, se verifica si el número de posiciones es menor que el tamaño de la lista (positions < size). Si no se cumple esta condición, se lanza una excepción ListSEException con el mensaje "La posición proporcionada excede el tamaño de la lista".
     * Si la identificación del primer elemento (head.getData().getIdentification()) coincide con la identificación proporcionada, se realiza el movimiento. Se crea un nuevo nodo (node) con los datos del siguiente nodo (head.getNext().getData()), luego se llama al método addByPosition para agregar el nodo en la posición positions + 1 y finalmente se actualiza el nodo inicial (head) para que apunte al siguiente nodo (head = head.getNext()).
     * Si la identificación del primer elemento no coincide con la identificación proporcionada, se sigue un proceso similar al caso anterior. Se inicializa una variable count en 1 y se crea un nodo temporal (temp) que apunta al nodo inicial (head) de la lista original.
     * Se inicia un bucle while que continúa hasta que la identificación del siguiente dato (temp.getNext().getData().getIdentification()) coincida con la identificación proporcionada. Esto se hace para encontrar el nodo que contiene la identificación que se desea mover.
     * Dentro del bucle, se actualiza el nodo temporal para que apunte al siguiente nodo (temp = temp.getNext()) y se incrementa la variable count en 1 para realizar un seguimiento de la posición actual.
     * Después de salir del bucle, se ha encontrado el nodo que contiene la identificación que se desea mover.
     * Se crea un nuevo nodo (temp2) con los datos del nodo que se desea mover (temp.getNext().getData()).
     * Se modifica la estructura de la lista para mover el nodo. Primero, se establece el enlace del nodo actual (temp) para saltar al siguiente nodo (temp.getNext().getNext()) y omitir el nodo que se está moviendo.
     * Luego, se llama al método addByPosition para agregar el nodo movido a una posición calculada (count + 1 + positions).
     * Si la lista original estaba vacía inicialmente (es decir, el nodo inicial era nulo), se lanza una excepción ListSEException con el mensaje "La lista se encuentra vacía".
     */
    public void afterwardsPositions(String identification, int positions) throws ListSEException {
        if (head != null) {
            if (positions < size) {
                if (head.getData().getIdentification() == identification) {
                    Node node = new Node(head.getNext().getData());
                    addByPosition(positions + 1, node.getData());
                    head = head.getNext();
                } else {
                    int count = 1;
                    Node temp = head;
                    while (temp.getNext().getData().getIdentification() != identification) {
                        temp = temp.getNext();
                        count++;
                        if (temp.getNext() != null) {
                            return;
                        }
                    }
                    Node temp2 = new Node(temp.getNext().getData());
                    temp.setNext(temp.getNext().getNext());
                    addByPosition(count + 1 + positions, temp2.getData());
                }
            } else {
                throw new ListSEException("La posición proporcionada excede el tamaño de la lista");
            }
        } else {
            throw new ListSEException("La lista se encuentra vacía.");
        }
    }

    /**
     * -Ejercicio 9: Obtener un informe de niños por rango de edades
     * Explicación:
     * El método rangeByAge toma dos parámetros: min, que es el valor mínimo del rango de edades, y max, que es el valor máximo del rango de edades.
     * El método verifica si el nodo inicial (head) de la lista es nulo. Si es así, significa que la lista está vacía y se lanza una excepción ListSEException con el mensaje "La lista se encuentra vacía".
     * Si la lista no está vacía, se inicializa un nodo temporal (temp) que apunta al nodo inicial (head) de la lista original.
     * Se inicializa una variable count en 0 para contar la cantidad de elementos cuyas edades están dentro del rango.
     * Se inicia un bucle while que continúa hasta que el nodo temporal sea nulo.
     * Dentro del bucle, se verifica si la edad del dato del nodo actual (temp.getData().getAge()) está dentro del rango especificado (temp.getData().getAge() >= min && temp.getData().getAge() <= max). Si es así, se incrementa la variable count en 1.
     * Después de verificar la edad, se actualiza el nodo temporal para que apunte al siguiente nodo (temp = temp.getNext()).
     * El bucle continúa hasta que no queden más nodos en la lista.
     * Finalmente, se devuelve el valor de count, que representa la cantidad de elementos cuyas edades están dentro del rango especificado.
     *
     */
    public int rangeByAge(int min, int max) throws ListSEException {
        if (head == null) {
            throw new ListSEException("La lista se encuentra vacía.");
        }
        Node temp = head;
        int count = 0;
        while (temp != null) {
            if (temp.getData().getAge() >= min && temp.getData().getAge() <= max) {
                count++;
            }
            temp = temp.getNext();
        }
        return count;
    }


    /**
     * -Ejercicio 10: Implementar un método que me permita enviar al final de la lista a los niños que
     * su nombre inicie con una letra dada
     * Explicación:
     *El método kidsByLetter toma un parámetro initial, que es la letra inicial que se utilizará para separar y reorganizar los niños en la lista.
     * El método verifica si el nodo inicial (head) de la lista es nulo. Si es así, significa que la lista está vacía y se lanza una excepción ListSEException con el mensaje "La lista está vacía".
     * Si la lista no está vacía, se crea una nueva lista enlazada llamada listCP que se utilizará para almacenar los niños reorganizados.
     * Se inicializa un nodo temporal (temp) que apunta al nodo inicial (head) de la lista original.
     * Se inicia un bucle while que continúa hasta que el nodo temporal sea nulo.
     * Dentro del bucle, se verifica si la letra inicial del nombre del dato del nodo actual (temp.getData().getName().charAt(0)) es diferente de la letra inicial proporcionada (Character.toUpperCase(initial)). Si es así, se agrega el nodo y sus datos a la lista listCP utilizando el método add.
     * Después de verificar la letra inicial, se actualiza el nodo temporal para que apunte al siguiente nodo (temp = temp.getNext()).
     * El bucle continúa hasta que no queden más nodos en la lista original.
     * Después de salir del primer bucle, se restablece el nodo temporal para que apunte nuevamente al nodo inicial de la lista original (temp = this.head).
     * Se inicia otro bucle while similar al anterior, que recorre la lista original nuevamente.
     * Dentro de este segundo bucle, se verifica si la letra inicial del nombre del dato del nodo actual es igual a la letra inicial proporcionada. Si es así, se agrega el nodo y sus datos a la lista listCP utilizando el método add.
     * Después de verificar la letra inicial en este segundo bucle, se actualiza el nodo temporal para que apunte al siguiente nodo (temp = temp.getNext()).
     * El segundo bucle continúa hasta que no queden más nodos en la lista original.
     * Finalmente, se actualiza el nodo inicial (head) de la lista original para que apunte al nodo inicial de la lista listCP, que contiene los niños reorganizados.
     */
    public void kidsByLetter(char initial) throws ListSEException {

        if (this.head == null) {
            throw new ListSEException("La lista está vacía");
        }

        ListSE listCP = new ListSE();
        Node temp = this.head;

        while (temp != null) {
            if (temp.getData().getName().charAt(0) != Character.toUpperCase(initial)) {
                listCP.add(temp.getData());
            }
            temp = temp.getNext();
        }
        temp = this.head;
        while (temp != null) {
            if (temp.getData().getName().charAt(0) == Character.toUpperCase(initial)) {
                listCP.add(temp.getData());
            }
            temp = temp.getNext();
        }
        this.head = listCP.getHead();
    }

    public void addByPosition(int position, Kid kid) throws ListSEException {
        if (head != null) {
            if (position == 1) {
                addToStart(kid);
            } else {
                Node temp = head;
                int cont = 1;
                while (temp != null && cont < position - 1) {
                    temp = temp.getNext();
                    cont++;
                }
                if (temp != null) {
                    Node newNode = new Node(kid);
                    newNode.setNext(temp.getNext());
                    temp.setNext(newNode);

                } else {
                    add(kid);
                }
            }
        } else {
            add(kid);
        }
    }

    /**
     * si hay datos en la cabeza y si en el siguiente nodo también hay datos
     * si
     * llamo a un ayudante y le digo que se posicione en la cabeza
     * que el ayudante recorra la lista hasta que llegue al último (mientras hayan datos)
     * que el ayudante tome el siguiente nodo (o se pase al final)
     * se guarda la cabeza en una variable copia de niños
     * que se reemplace la cabeza con el objeto de datos del último nodo (el primero niño ahora es último)
     * que se reemplace el último nodo (el último ahora es el primero)
     */
    public void changesExtremes() throws ListSEException {
        if (this.head != null && this.head.getNext() != null) {
            Node temp = this.head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }

            Kid copy = this.head.getData();
            this.head.setData(temp.getData());
            temp.setData(copy);
        } else {
            throw new ListSEException("No es posible cambiar de extremos.");
        }
    }

    /**
     * Adicionar al inicio
     * si hay datos
     * si
     * meto al niño en un costal (nuevocostal)
     * le digo a nuevo costal que tome con su brazo a la cabeza
     * cabeza es igual a nuevo costal
     * no
     * meto el niño en un costal y lo asigno a la cabeza
     */
    public void addToStart(Kid kid) throws ListSEException {
        if (head != null) {
            Node newNode = new Node(kid);
            newNode.setNext(head);
            head = newNode;
        } else if (this.head == null) {
            head = new Node(kid);
        } else {
            throw new ListSEException("La lista está vacía");
        }
        size++;
    }

}
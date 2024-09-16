Para probar los distintos aspectos de envers se deberan hacer 3 cosas para ver la mayor funcionalidad posible\
-  ejecutar desde main  para que se creen las tablas y se creen registros en ellas, dado eso se pueden ver los registros en h2\
- Para comprobar la auditoria se debe comentar en el archivo main las  creaciones  de registros y descomentar la actualizacion de una factura, esto ya esta indicado en el código, despues se debe cambiar el valor a "update" en el persistence.xml para que actualice la base de datos asi no crea nuevamente las tablas y se puede ver la auditoria funcionando en la tabla de auditoria de la factura.\
- Ahora es lo mismo siguiendo la auditoria para cuando se elimina un registro, en este caso una factura, nuevamente se indica en el código como se hace, hay que descomentar la seccion y comentar todo lo anterior para poder ver los cambios en la tabla de auditoria de la factura.


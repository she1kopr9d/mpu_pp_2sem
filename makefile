.PHONY: create-project

create-project:
	@read -p "Введите название проекта: " project_name; \
	if [ -z "$$project_name" ]; then \
		echo "Ошибка: имя не может быть пустым."; \
		exit 1; \
	fi; \
	if [ -d "$$project_name" ]; then \
		echo "Ошибка: папка '$$project_name' уже существует."; \
		exit 1; \
	fi; \
	echo "Создаём проект '$$project_name'..."; \
	mkdir -p "$$project_name"/src/main/java/com/example; \
	mkdir -p "$$project_name"/src/main/resources; \
	mkdir -p "$$project_name"/target; \
	echo "public class Main {" > "$$project_name"/src/main/java/com/example/Main.java; \
	echo "    public static void main(String[] args) {" >> "$$project_name"/src/main/java/com/example/Main.java; \
	echo "        System.out.println(\"Hello, $$project_name!\");" >> "$$project_name"/src/main/java/com/example/Main.java; \
	echo "    }" >> "$$project_name"/src/main/java/com/example/Main.java; \
	echo "}" >> "$$project_name"/src/main/java/com/example/Main.java; \
	printf '%s\n' \
		'.PHONY: compile run clean' \
		'' \
		'compile:' \
		'\tmkdir -p target/classes' \
		'\tjavac -d target/classes src/main/java/com/example/*.java' \
		'' \
		'run: compile' \
		'\tjava -cp target/classes com.example.Main' \
		'' \
		'clean:' \
		'\trm -rf target' \
		> "$$project_name"/Makefile; \
	echo "Проект '$$project_name' успешно создан."; \
	echo "Структура:"; \
	find "$$project_name" -type f -o -type d | sort; \
	echo ""; \
	echo "Внутренний Makefile добавлен. Используйте:"; \
	echo "  cd $$project_name && make compile  # собрать"; \
	echo "  cd $$project_name && make run      # собрать и запустить"; \
	echo "  cd $$project_name && make clean    # очистить"
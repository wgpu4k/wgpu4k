use std::path::Path;

pub fn int_times_2(int: i32) -> i32 {
    let new_int: i32 = int * 2;
    new_int
}

pub fn return_string(old_string: String) -> String {
    let mut new_string: String = "New String + ".to_string();
    new_string.push_str(&old_string);
    new_string
}

pub fn get_file_status(path: &str) -> bool {
    let file_path = path.to_owned() + "file.txt";
    Path::new(&file_path).exists()
}

pub fn split_string_into_words(sentence: String) -> Vec<String> {
    let words_vector: Vec<String> = sentence.split(' ').map(String::from).collect();
    words_vector
}

#[cfg(test)]
mod example_tests {
    use super::*;

    mod example_main_tests {
        use super::*;

        #[test]
        fn int_times_two_test() {
            let int: i32 = 4;
            assert!(int_times_2(int) == 8);
        }

        #[test]
        fn return_string_test() {
            let old_string: String = "Old String".to_string();
            assert!(return_string(old_string) == "New String + Old String".to_string());
        }

        #[test]
        fn get_file_status_test() {
            assert!(!get_file_status(""));
        }

        #[test]
        fn split_string_into_words_test() {
            assert!(
                split_string_into_words("Eine Insel mit zwei Bergen".to_string())
                    == ["Eine", "Insel", "mit", "zwei", "Bergen"]
            );
        }
    }
}

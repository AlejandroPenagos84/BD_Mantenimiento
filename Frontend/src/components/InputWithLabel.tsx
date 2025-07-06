import { useFormContext } from "react-hook-form";
import { Input } from "./ui/input";
import type { InputHTMLAttributes } from "react";

import {
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form";

type Props<S> = {
  fieldTitle: string;
  nameInSchema: keyof S & string;
  placeholderTitle: string;
  className?: string;
} & InputHTMLAttributes<HTMLInputElement>;

export function InputWithLabel<S>({
  fieldTitle,
  nameInSchema,
  placeholderTitle,
  className,
}: Props<S>) {
  const form = useFormContext();

  return (
    <FormField
      control={form.control}
      name={nameInSchema}
      render={({ field }) => (
        <FormItem>
          <FormLabel
            className="text-base"
            htmlFor={nameInSchema}
          >
                {fieldTitle}
          </FormLabel>
          <FormControl>
            <Input
              placeholder={placeholderTitle}
              className={`w-full disabled:text-blue-500 dark:disabled:text-green-500 ${className} text-sm md:text-base`}
              {...field}
            />
          </FormControl>
          <FormMessage />
        </FormItem>
      )}
    />
  );
}

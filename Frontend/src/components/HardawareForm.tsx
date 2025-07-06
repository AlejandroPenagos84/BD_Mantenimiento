import { z } from "zod";
import { zodResolver } from "@hookform/resolvers/zod";
import { useForm } from "react-hook-form";
import { InputWithLabel } from "./InputWithLabel";
import { Form } from "@/components/ui/form";
import { Button } from "./ui/button";
import { SelectWithLabel } from "./SelectWithLabel";



const clients = [
  { id: "1", description: "Clínica Santa María" },
  { id: "2", description: "Hospital Central" },
  { id: "3", description: "Laboratorio BioMed" },
];


const clientHardwareSchema = z.object({
  client: z.string().nonempty("El cliente es obligatorio"),
  equipmentType: z.string().nonempty("El tipo de equipo es obligatorio"),
  serial: z.string().nonempty("La serie es obligatoria"),
  invimaRegistration: z.string().nonempty("El registro Invima es obligatorio"),
  inventoryNumber: z
    .string()
    .optional()
    .refine((val) => !val || val.length >= 3, {
      message:
        "El número de inventario debe tener al menos 3 caracteres si se proporciona",
    }),
  brand: z.string().nonempty("La marca es obligatoria"),
  model: z.string().nonempty("El modelo es obligatorio"),
  riskType: z.string().nonempty("El tipo de riesgo es obligatorio"),
  site: z.string().nonempty("La sede es obligatoria"),
  serviceArea: z.string().nonempty("El área de servicio es obligatoria"),
});

type clientHarwareType = z.infer<typeof clientHardwareSchema>;

export function ClientHarwareForm() {
   
  const form = useForm<clientHarwareType>({
    mode: "onBlur",
    resolver: zodResolver(clientHardwareSchema),
    defaultValues: {
      client: "",
      equipmentType: "",
      serial: "",
      invimaRegistration: "",
      inventoryNumber: "", // aunque es optional, mejor dejarlo como string vacío
      brand: "",
      model: "",
      riskType: "",
      site: "",
      serviceArea: ""
    },
  });

  const onSubmit = (data: clientHarwareType) => {
    console.log("Formulario enviado con éxito:", data);
  };

  return (
    <>
    <div className="mt-5">
        <h1 className="mx-auto text-md md:text-lg font-bold text-center">
            Datos Equipo
        </h1>
        <hr className="mx-auto w-full bg-[#C5C6C7] h-1 mt-3 mb-5 border-0" />
    </div>
        <Form {...form}>
        <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-6">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
            <SelectWithLabel
                fieldTitle="Cliente"
                nameInSchema="client"
                data={clients}
            />
            <InputWithLabel
                fieldTitle="Tipo de Equipo"
                nameInSchema="equipmentType"
                placeholderTitle="Por favor, selecciona"
            />
            <InputWithLabel
                fieldTitle="Serie"
                nameInSchema="serial"
                placeholderTitle="Serie"
            />
            <InputWithLabel
                fieldTitle="Registro Invima"
                nameInSchema="invimaRegistration"
                placeholderTitle="Registro Invima"
            />
            <InputWithLabel
                fieldTitle="Número de Inventario"
                nameInSchema="inventoryNumber"
                placeholderTitle="Número de Inventario (opcional)"
            />
            <InputWithLabel
                fieldTitle="Marca"
                nameInSchema="brand"
                placeholderTitle="Por favor, selecciona"
            />
            <InputWithLabel
                fieldTitle="Modelo"
                nameInSchema="model"
                placeholderTitle="Por favor, selecciona"
            />
            <InputWithLabel
                fieldTitle="Tipo de Riesgo"
                nameInSchema="riskType"
                placeholderTitle="Por favor, selecciona"
            />
            <InputWithLabel
                fieldTitle="Sede"
                nameInSchema="site"
                placeholderTitle="Por favor, selecciona"
            />
            <InputWithLabel
                fieldTitle="Área de Servicio"
                nameInSchema="serviceArea"
                placeholderTitle="Por favor, selecciona"
            />
            </div>

            <Button type="submit" title="Enviar">Enviar</Button>
        </form>
        </Form>
    </>
  );
}
